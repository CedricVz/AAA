package com.example.x15011071.audioacousticassistant_app;
/*
* @filename SplashScreen.java
* @author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
* *@reference YouTube - Humayun Kabir - Splash screen tutorial - https://www.youtube.com/watch?v=ND6a4V-xdjI
* @date 11 April 2017
 */
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

public class SplashScreen extends AppCompatActivity {
    private static int WELCOME_TIMEOUT = 3500;
    VideoView videoView;


    //@reference Youtube - Humayun Kabir -  Splash screen tutorial & @authors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        //Declares the videoview in the xml page (splash_screen)
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        //Defines the location of the video within the folder raw.
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.sequence));
        //This declares the media controller and starts it
        videoView.setMediaController(new MediaController(this));
        videoView.start();

        //When the video is finished, the splash screen sends the user to the main activity
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent welcome = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(welcome);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        }, WELCOME_TIMEOUT);

        //This tells the app to run the splash screen for 3.5 seconds
        //Which is the exact length of the video being run from the raw folder
        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(3500);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        myThread.start();

    }
}