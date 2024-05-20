package com.example.timerwcpp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.timerwcpp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Boolean timerIsRunning;
    private Handler timerHandler;
    private Runnable timerRunnable;

    // Used to load the 'timerwcpp' library on application startup.
    static {
        System.loadLibrary("timerwcpp");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.timerwcpp.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Displayed Objects
        TextView timerDisplay = findViewById(R.id.timeElapsedDisplay);
        ImageButton playPauseBtn = findViewById(R.id.playPauseButton);
        ImageButton prevBtn = findViewById(R.id.prevBtn);
        ImageButton nextBtn = findViewById(R.id.nextBtn);

        // Initialize timer
        timerDisplay.setText(R.string.reset_timer_display);
        timerIsRunning = false;

        // Timer handler and runnable to update the UI
        timerHandler = new Handler(Looper.getMainLooper());
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                timerDisplay.setText(getElapsedTime());
                if (timerIsRunning) {
                    timerHandler.postDelayed(this, 1000);
                }
            }
        };

        // Button click listeners
        playPauseBtn.setOnClickListener(view -> {
            if (timerIsRunning) {
                playPauseBtn.setImageResource(R.drawable.ic_baseline_play_icon);
                stopTimer();
            } else {
                playPauseBtn.setImageResource(R.drawable.ic_baseline_pause_icon);
                startTimer();
                timerHandler.post(timerRunnable);
            }
            timerIsRunning = !timerIsRunning;
        });

        prevBtn.setOnClickListener(view -> {
            resetTimer();
            timerDisplay.setText(R.string.reset_timer_display);
            if (timerIsRunning) {
                playPauseBtn.setImageResource(R.drawable.ic_baseline_play_icon);
                timerHandler.removeCallbacks(timerRunnable);
                timerIsRunning = false;
            }
        });

        nextBtn.setOnClickListener(view -> {
            resetTimer();
            timerDisplay.setText(R.string.reset_timer_display);
            if (timerIsRunning) {
                playPauseBtn.setImageResource(R.drawable.ic_baseline_play_icon);
                timerHandler.removeCallbacks(timerRunnable);
                timerIsRunning = false;
            }
        });
    }

    private void initializeTimer(){

    }

    public native void startTimer();
    public native void stopTimer();
    public native void resetTimer();
    public native String getElapsedTime();
}
