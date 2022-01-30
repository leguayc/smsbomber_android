package com.example.smsbomber.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.smsbomber.models.SmsListModel;

public class SMSService extends Service {
    private static final String TAG = "SMSService";
    private static final String SMS_RECEIVED =
            "android.provider.Telephony.SMS_RECEIVED";
    private boolean active;
    private int countSMSReceived;

    private class SMSReceiver extends BroadcastReceiver {
        private SmsListModel smsListModel;
        public SMSReceiver() {
            Log.v("SMSReceiver", "SMSReceiver()");
            smsListModel = new SmsListModel();
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("SMSReceiver", "onReceive()");
            intent.getData();
            //if (active) smsListModel.addSms(sms);
        }
    }

    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter(SMS_RECEIVED);
        registerReceiver(new SMSReceiver(), filter);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

