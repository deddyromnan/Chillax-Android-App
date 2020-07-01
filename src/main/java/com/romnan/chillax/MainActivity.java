package com.romnan.chillax;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView rainBtn = findViewById(R.id.rainBtn);
        rainBtn.setOnClickListener(this);

        final ImageView bonfireBtn = findViewById(R.id.bonfireBtn);
        bonfireBtn.setOnClickListener(this);
    }

    private void createNextMediaPlayer() {
        mNextPlayer = MediaPlayer.create(this, mResId);
        mCurrentPlayer.setNextMediaPlayer(mNextPlayer);
        mCurrentPlayer.setOnCompletionListener(onCompletionListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rainBtn:
                mResId = R.raw.raining_sound;
                setCurrentPlayer();
                break;
            case R.id.bonfireBtn:
                mResId = R.raw.bonfire_sound;
                setCurrentPlayer();
                break;
        }
    }
}