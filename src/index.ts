import { registerPlugin } from '@capacitor/core';

import type { SmsGetterPlugin } from './definitions';

const SmsGetter = registerPlugin<SmsGetterPlugin>('SmsGetter', {
  web: () => import('./web').then(m => new m.SmsGetterWeb()),
});

export * from './definitions';
export { SmsGetter };
