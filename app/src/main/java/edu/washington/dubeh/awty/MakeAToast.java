package edu.washington.dubeh.awty;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class MakeAToast implements Runnable{
    private final Context context;
    private String message;
    private String number;

    MakeAToast(Context context, String message, String number) {
        this.context = context;
        this.message = message;
        this.number = number;
    }

    @Override
    public void run() {
        String formattedNumber = "(" + number.substring(0, 3) + ") " +
                number.substring(3, 6) + "-" + number.substring(6, 10);
        Toast.makeText(context, formattedNumber + ": " + message,
                    Toast.LENGTH_SHORT).show();
    }
}
