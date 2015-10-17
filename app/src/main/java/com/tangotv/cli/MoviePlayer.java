package com.tangotv.cli;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.netspace.tango.tangotv.R;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MoviePlayer extends Activity {

    private ProgressDialog progDialog;
    private VideoView videoView;
    boolean shouldExecuteOnResume;

    @Override
    protected void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.movie_player);
        videoView = (VideoView)findViewById(R.id.myVideo);
        shouldExecuteOnResume = false;

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String vidURL = extras.getString("VIDEO_URL");
            /*try {
                URL url = new URL(vidURL);
                vidURL  = url.getHost() + URLEncoder.encode(url.getFile(), "UTF-8");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }*/
            doPlayback(vidURL);
        } else {

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if(shouldExecuteOnResume){
            //call intent to a movie browser activity
            Intent MovieBrowser = new Intent(this, MovieBrowserGrid.class);//MovieBrowser.class
            startActivity(MovieBrowser);
        } else{
            shouldExecuteOnResume = true;
        }
    }

    private void doPlayback(final String playbackUrl) {
       /* final MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);*/
       // Uri playbackUri = Uri.parse(playbackUrl);
        //videoView.setVideoURI(playbackUri);

         Intent intent = new Intent(Intent.ACTION_VIEW);
         intent.setDataAndType(Uri.parse(playbackUrl), "video/*");
         startActivity(Intent.createChooser(intent, "Complete action using"));


        progDialog = ProgressDialog.show(this, "Tafadhali subiri", "Tunapakua Filamu...", true);

       /* videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progDialog.dismiss();
                mp.start();
            }
        });*/

       /* videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                if (what == 100) {
                    videoView.stopPlayback();
                    doPlayback(playbackUrl);
                }

                return true;
            }
        });

        videoView.start();*/
    }
}
