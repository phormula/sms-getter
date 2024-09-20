package com.phormula.sms.getter;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.getcapacitor.Bridge;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.getcapacitor.JSObject;

@CapacitorPlugin(name = "SmsGetter", permissions = {
        @Permission(strings = { Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS }, alias = "sms")
})
public class SmsGetterPlugin extends Plugin {

    private SmsGetter implementation = new SmsGetter();

    private static final String TAG = "SmsReceiverPlugin";

    private BroadcastReceiver smsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String messageBody = "";
            String sender = "";

            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        sender = msgs[i].getOriginatingAddress();
                        messageBody += msgs[i].getMessageBody();
                    }

                    // Log SMS details
                    Log.d(TAG, "Message from: " + sender + ", Body: " + messageBody);

                    // Send the SMS data back to the web app
                    notifyWebApp(sender, messageBody);
                }
            }
        }
    };

    @Override
    public void load() {
        super.load();

        // Register the SMS receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        getContext().registerReceiver(smsReceiver, filter);
    }

    @PermissionCallback
    private void smsPermissionCallback(PluginCall call) {
        if (getPermissionState("sms") == PermissionState.GRANTED) {
            startReceivingSms(call);
        } else {
            call.reject("SMS permission not granted");
        }
    }

    private void notifyWebApp(String sender, String messageBody) {
        // Notify the web app with SMS details
        JSObject ret = new JSObject();
        ret.put("sender", sender);
        ret.put("message", messageBody);
        notifyListeners("smsReceived", ret);
    }

    @PluginMethod
    public void startWatch(PluginCall call) {
        if (getPermissionState("sms") != PermissionState.GRANTED) {
            requestPermissionForAlias("sms", call, "smsPermissionCallback");
        } else {
            startReceivingSms(call);
        }
    }

    private void startReceivingSms(PluginCall call) {
        call.resolve();
    }

    @Override
    protected void handleOnDestroy() {
        super.handleOnDestroy();
        // Unregister the SMS receiver when the plugin is destroyed
        getContext().unregisterReceiver(smsReceiver);
    }
}
