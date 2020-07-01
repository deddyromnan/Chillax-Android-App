package com.romnan.chillax;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mCurrentPlayer = new MediaPlayer(), mNextPlayer;
    private int mResId;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mp.release();
            mCurrentPlayer = mNextPlayer;
            createNextMediaPlayer();
            Log.d("MediaPlayer", "Looped");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView rainBtn = findViewById(R.id.rainBtn);
        rainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResId = R.raw.raining_sound;
                setCurrentPlayer();
            }
        });
    }

    private void setCurrentPlayer() {
        if (!mCurrentPlayer.isPlaying()) {
            mCurrentPlayer = MediaPlayer.create(MainActivity.this, mResId);
            mCurrentPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mCurrentPlayer.start();
                }
            });
        } else {
            mCurrentPlayer.pause();
        }
    }

    private void createNextMediaPlayer() {
        mNextPlayer = MediaPlayer.create(this, mResId);
        mCurrentPlayer.setNextMediaPlayer(mNextPlayer);
        mCurrentPlayer.setOnCompletionListener(onCompletionListener);
    }
}