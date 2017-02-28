package edu.washington.dubeh.awty;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class MakeAToast implements Runnable{
    private String message;
    private String number;

    MakeAToast(String message, String number) {
        this.message = message;
        this.number = number;
    }

    @Override
    public void run() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, message, null, null);
    }
}
