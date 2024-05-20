package com.example.timerwcpp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.timerwcpp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Boolean timerIsRunning;

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

        // Button click listeners
        playPauseBtn.setOnClickListener(view -> {
            if(timerIsRunning){     // timerIsRunning == true
                playPauseBtn.setImageResource(R.drawable.ic_baseline_play_icon);
                timerIsRunning = false;
            } else {    // timerIsRunning == false
                playPauseBtn.setImageResource(R.drawable.ic_baseline_pause_icon);
                timerIsRunning = true;
            }
        });

        prevBtn.setOnClickListener(view -> {

        });

        nextBtn.setOnClickListener(view -> {

        });
    }

//    public native String stringFromJNI();
}