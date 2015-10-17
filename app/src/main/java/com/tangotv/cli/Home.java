package com.tangotv.cli;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.netspace.tango.tangotv.R;


public class Home extends Activity {

    // private Animation animZoomOut;
    // private Animation animZoomIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        //get category wrapper and app wrapper layout ids
        //access their child views
        View musicButton    = findViewById(R.id.category_selector_highlight_music);
        View movieButton    = findViewById(R.id.category_selector_highlight_movie);
        View seriesButton   = findViewById(R.id.category_selector_highlight_series);
        View settingsButton = findViewById(R.id.category_selector_highlight_settings);


        View youtubeBtn     = findViewById(R.id.app_selector_highlight_youtube);
        View facebookBtn    = findViewById(R.id.app_selector_highlight_facebook);
        View instagramBtn   = findViewById(R.id.app_selector_highlight_instagram);
        View twitterBtn     = findViewById(R.id.app_selector_highlight_twitter);
        // View app_wrapper = findViewById(R.id.apps_layout);


        musicButton
                .setOnFocusChangeListener(new FocusAnimation(this));
        movieButton
                .setOnFocusChangeListener(new FocusAnimation(this));
        seriesButton
                .setOnFocusChangeListener(new FocusAnimation(this));
        settingsButton
                .setOnFocusChangeListener(new FocusAnimation(this));
        youtubeBtn
                .setOnFocusChangeListener(new FocusAnimation(this));
        facebookBtn
                .setOnFocusChangeListener(new FocusAnimation(this));
        instagramBtn
                .setOnFocusChangeListener(new FocusAnimation(this));
        twitterBtn
                .setOnFocusChangeListener(new FocusAnimation(this));
    }

    public void launchMovieActivity(View view){
        // Call intent to a movie browser activity
        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.tangotv.tvcards");
        startActivity(LaunchIntent);

        // Intent MovieBrowser = new Intent(this, MovieBrowserGrid.class);//MovieBrowser.class
        // startActivity(MovieBrowser);

        /*
         * Intent MovieBrowser = new Intent(this,  MovieBrowser.class);//MovieBrowser.class
         * startActivity(MovieBrowser);
         */

    }

    public void launchMusicActivity(View view){
        String url = "https://www.youtube.com/user/MziikiTube";
        Intent intent=null;
        try {
            intent =new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.youtube.tv");
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }
    public void launchUbungoActivity(View view){
        String url = "https://www.youtube.com/user/ubongokids";
        Intent intent=null;
        try {
            intent =new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.youtube.tv");
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }
  /*  public void launchSettings(){
        startActivity(new Intent(Settings.ACTION_SETTINGS));
    }*/
    public void  launchSettingManager(View view) {
        startActivity(new Intent(Settings.ACTION_SETTINGS));
    }
    public void launchYoutubeActivity(View view){
        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube.tv");
        startActivity(LaunchIntent);
    }
    public void launchFacebookActivity(View view){
        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
        startActivity(LaunchIntent);
    }

    public void launchInstagramActivity(View view){
        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        startActivity(LaunchIntent);
    }

    public void launchTwitterActivity(View view){
        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.twitter.android");
        startActivity(LaunchIntent);
    }
}
