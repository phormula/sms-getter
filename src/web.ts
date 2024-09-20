import { WebPlugin } from '@capacitor/core';

import type { SmsGetterPlugin } from './definitions';

export class SmsGetterWeb extends WebPlugin implements SmsGetterPlugin {
  async startWatch() {
    // Calls the native Android method to start watching for incoming SMS
    return new Promise<void>((resolve, reject) => {
      try {
        this.notifyListeners('smsReceived', {
          sender: '',
          message: '',
        });
        resolve();
      } catch (error) {
        reject(error);
      }
    });
  }

  addListener(
    eventName: 'smsReceived',
    listenerFunc: (data: { sender: string; message: string }) => void,
  ) {
    return super.addListener(eventName, listenerFunc);
  }
}
