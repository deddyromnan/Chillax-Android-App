package com.romnan.chillax;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.raining_sound);

        final ImageView rainBtn = findViewById(R.id.rainBtn);
//        rainBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!mediaPlayer.isPlaying()) {
//                    Snackbar.make(rainBtn, "Gentle rain sound started...",
//                            Snackbar.LENGTH_LONG).show();
//                    mediaPlayer.start();
//                    mediaPlayer.setLooping(true);
//                } else {
//                    Snackbar.make(rainBtn, "Gentle rain stopped...",
//                            Snackbar.LENGTH_LONG).show();
//                    mediaPlayer.pause();
//                }
//            }
//        });

        rainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoopMediaPlayer.create(MainActivity.this, R.raw.raining_sound);
            }
        });
    }
}