package edu.washington.dubeh.awty;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    boolean start = true;
    Button startStop;
    EditText message;
    EditText phoneNumber;
    EditText time;
    Intent toastIntent;
    public static final String MAIN_MESSAGE = "Main Message";
    public static final String MAIN_NUMBER = "Main Number";
    public static final String MAIN_TIME = "Main Time";
    public static boolean MAIN_CONTINUE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startStop = (Button) findViewById(R.id.startStop);
        message = (EditText) findViewById(R.id.message);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        time = (EditText) findViewById(R.id.time);
        toastIntent = new Intent(MainActivity.this, ToastIntentService.class);

        startStop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String messageText = message.getText().toString();
                String number = phoneNumber.getText().toString();
                String timeText = time.getText().toString();
                if(start && number.length() == 10 && Integer.valueOf(timeText) > 0 && message.length() > 0) {
                    start = false;
                    startStop.setText("Stop");
                    startStop.setBackgroundColor(Color.parseColor("#ff0000"));
                    toastIntent.putExtra(MAIN_MESSAGE, messageText);
                    toastIntent.putExtra(MAIN_NUMBER, number);
                    toastIntent.putExtra(MAIN_TIME, timeText);
                    MAIN_CONTINUE = true;
                    startService(toastIntent);
                } else if(!start) {
                    start = true;
                    startStop.setText("Start");
                    startStop.setBackgroundColor(Color.parseColor("#00ff00"));
                    MAIN_CONTINUE = false;
                    stopService(toastIntent);
                } else {
                    String problem = "";
                    if(message.length() < 1 || number.length() < 1 || timeText.length() < 1) {
                        problem = "You must fill out all the fields";
                    } else if(number.length() != 10) {
                        problem = "You must put in a complete phone number (10 digits)";
                    } else if(Integer.valueOf(timeText) < 1) {
                        problem = "Input time must be at least 1 minute";
                    }
                    Toast.makeText(MainActivity.this, problem, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
