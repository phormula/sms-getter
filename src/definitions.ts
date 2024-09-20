import { PluginListenerHandle } from '@capacitor/core';

export interface SmsGetterPlugin {
  /**
   * Starts the SMS receiver to watch for incoming SMS.
   * @returns Promise that resolves when the SMS watch is started.
   */
  startWatch(): Promise<void>;

  /**
   * Adds an event listener for the 'smsReceived' event.
   * @param eventName The event name, which is 'smsReceived'.
   * @param listenerFunc The function to call when the SMS is received.
   */
  addListener(
    eventName: 'smsReceived',
    listenerFunc: (data: { sender: string; message: string }) => void,
  ): Promise<PluginListenerHandle>;
}
