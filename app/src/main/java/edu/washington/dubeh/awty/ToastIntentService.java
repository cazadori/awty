package edu.washington.dubeh.awty;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class ToastIntentService extends IntentService {
    Handler handler;
    String message;
    String number;
    String time;

    public ToastIntentService() {
        super("ToastIntentService");
        handler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        message = intent.getStringExtra(MainActivity.MAIN_MESSAGE);
        number = intent.getStringExtra(MainActivity.MAIN_NUMBER);
        time = intent.getStringExtra(MainActivity.MAIN_TIME);
        int timeInt = Integer.valueOf(time);
        int timeMilli = timeInt*60*1000;

        while(true) {
            if(MainActivity.MAIN_CONTINUE == false) {
                break;
            }
            try {
                handler.post(new MakeAToast(message, number));

                Thread.sleep(timeMilli);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
