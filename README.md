# sms-getter

Gets sms

## Install

```bash
npm install sms-getter
npx cap sync
```

## API

<docgen-index>

* [`startWatch()`](#startwatch)
* [`addListener('smsReceived', ...)`](#addlistenersmsreceived-)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### startWatch()

```typescript
startWatch() => Promise<void>
```

Starts the SMS receiver to watch for incoming SMS.

--------------------


### addListener('smsReceived', ...)

```typescript
addListener(eventName: 'smsReceived', listenerFunc: (data: { sender: string; message: string; }) => void) => Promise<PluginListenerHandle>
```

Adds an event listener for the 'smsReceived' event.

| Param              | Type                                                                 | Description                                    |
| ------------------ | -------------------------------------------------------------------- | ---------------------------------------------- |
| **`eventName`**    | <code>'smsReceived'</code>                                           | The event name, which is 'smsReceived'.        |
| **`listenerFunc`** | <code>(data: { sender: string; message: string; }) =&gt; void</code> | The function to call when the SMS is received. |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt;</code>

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |

</docgen-api>
