package com.example.smsbomber.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.smsbomber.models.SettingsModel;

public class SMSService extends Service {
    private static final String TAG = "SMSService";
    private static final String SMS_RECEIVED =
            "android.provider.Telephony.SMS_RECEIVED";
    private boolean active;
    private int countSMSReceived;
    private SMSReceiver smsReceiver;
    private SettingsModel model;

    private class SMSReceiver extends BroadcastReceiver {
        public SMSReceiver() {
            Log.v("SMSReceiver", "SMSReceiver()");
            model = new SettingsModel();
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("SMSReceiver", "onReceive()");

            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[])bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                }
                if (messages.length > -1) {
                    Log.i(TAG, "Message received: " + messages[0].getMessageBody());

                    String phoneNumber = messages[0].getOriginatingAddress();
                    if (phoneNumber != null && phoneNumber.length()>3) {

                        if (model.canSendAutomaticMessage()) {
                            SmsManager.getDefault().sendTextMessage(phoneNumber, null, model.getRandomMessage(), null, null);
                        }
                    }
                }
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter(SMS_RECEIVED);
        this.smsReceiver = new SMSReceiver();
        registerReceiver(this.smsReceiver, filter);
    }

    public void onDestroy() {
        unregisterReceiver(this.smsReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

