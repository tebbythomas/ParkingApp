package cse5236.parkingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class TimerActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "";
    private static final String EXTRA_MESSAGE = "";
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button startB;
    public TextView text;
    private final long startTime = 20 * 60 * 1000;
    private final long interval = 1 * 1000;
    public long currentTime = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        startB = (Button) this.findViewById(R.id.button);
        startB.setOnClickListener(this);
        text = (TextView) this.findViewById(R.id.timer);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        text.setText("0:00:00");
    }

    @Override
    public void onClick(View v) {
        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
            startB.setText("STOP");
        } else {
            countDownTimer.cancel();
            timerHasStarted = false;
            startB.setText("RESTART");
        }
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            text.setText("Time's up!");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long secondsUntilFinished = millisUntilFinished / 1000;
            long minsUntilFinished = secondsUntilFinished / 60;
            long hoursUntilFinished = minsUntilFinished / 60;
            minsUntilFinished = minsUntilFinished % 60;
            secondsUntilFinished = secondsUntilFinished % 60;
            currentTime = millisUntilFinished;
            text.setText("" + hoursUntilFinished + ":" + minsUntilFinished + ":" + secondsUntilFinished);
        }
    }

    public void home(View view) {


        Log.d(TAG, "home");

        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = "home" ;
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }



}