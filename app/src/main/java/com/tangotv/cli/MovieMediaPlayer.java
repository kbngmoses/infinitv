package com.tangotv.cli;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.Toast;

import com.netspace.tango.tangotv.R;

import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class  MovieMediaPlayer extends Activity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private MediaPlayer mediaPlayer;
    private SurfaceHolder vidHolder;
    private SurfaceView vidSurface;
    private MediaController mcontroller;

    private Handler handler = new Handler() {
        @Override
        public void close() {

        }

        @Override
        public void flush() {

        }

        @Override
        public void publish(LogRecord logRecord) {

        }
    };

    String vidAddress = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    //"rtsp://r2---sn-a5m7zu76.c.youtube.com/Ck0LENy73wIaRAnTmlo5oUgpQhMYESARFEgGUg5yZWNvbW1lbmRhdGlvbnIhAWL2kyn64K6aQtkZVJdTxRoO88HsQjpE1a8d1GxQnGDmDA==/0/0/0/video.3gp";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_media_player);

        vidSurface = (SurfaceView) findViewById(R.id.surfView);
        vidHolder = vidSurface.getHolder();
        vidHolder.addCallback(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDisplay(vidHolder);
            mediaPlayer.setDataSource(vidAddress);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mcontroller = new MediaController(this);
        }
        catch(Exception e){
            Toast.makeText(this, "Movie not found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();

        mcontroller.setAnchorView(vidSurface);

    }


}
