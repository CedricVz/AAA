package com.example.x15011071.audioacousticassistant_app;


/*
* @filename AdviceActivity.java
* @author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
* @reference Stack Overflow URL - https://stackoverflow.com/questions/5026349/how-to-open-a-website-when-a-button-is-clicked-in-android-application
* @reference StackOverflow FB - https://stackoverflow.com/questions/4810803/open-facebook-page-from-android-app
* @reference Stack Overflow SetIntent - https://stackoverflow.com/questions/6751564/how-to-pass-a-boolean-between-intents
* @date 11 April 2017

 */

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class AdviceActivity extends AppCompatActivity {

    Button urlBtn, facebookBtn, startOverBtn;
    TextView changingText;

    final double BED = 40;
    final double GREENSCREEN = 1.5;

    double youtubeAF, homeArtistAF, eventAF; //acoustic foam values

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);

        changingText = (TextView) findViewById(R.id.changingTextTV);

        startOverBtn = (Button)findViewById(R.id.startOverBtn);
        startOverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOver();
            }
        });

        //to get values from RecordActivity.java
        final boolean youtube = getIntent().getExtras().getBoolean("youtubeRec");
        final boolean homeArtist = getIntent().getExtras().getBoolean("homeArtistRec");
        final boolean event = getIntent().getExtras().getBoolean("eventRec");

        Bundle d = getIntent().getExtras();
        final double db = d.getDouble("db");

//Testing:
//        Toast.makeText(getApplicationContext(),"youtube " + youtubeRec,Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(),"homeArtist " + homeArtistRec,Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(),"event " +  eventRec,Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(),"db " +  db,Toast.LENGTH_SHORT).show();


//        final WebView webView = (WebView)findViewById(R.id.webViewWV);
//        setContentView(webView);

//        youtube kit = bedroom + greenscreen + AF
//        homeArtist kit = bedroom + AF
//        event kit = AF

        youtubeAF = (db - (BED + GREENSCREEN)) / 20;
        homeArtistAF = (db - GREENSCREEN) / 20;
        eventAF = db / 20;

        if (youtube == true) {
            changingText.setText("As a YouTuber, you will need to soften the sound of your voice echoing on your walls. It's recommended that you have " + youtubeAF + " square metres of acoustic foam on your walls. You can click the image below to purchase some foam from our store");
        } else if (homeArtist == true) {
            changingText.setText("As a Home Artist, as your perform, you will need to soften the sound of your voice against the walls. It's recommended that you have " + homeArtistAF + " square metres of acoustic foam on your wall. You can click the image below to purchase some foam from our store");
        } else if (event == true) {
            changingText.setText("As an Event Organiser, the artists' sounds will bounce off the walls. It is recommended that the walls are softened with " + eventAF + " square metres of acoustic foam. You can click the image below to purchase some foam from our store");
        }

        urlBtn = (Button) findViewById(R.id.urlBTN);
        urlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlAmazon();
            }
        });

        facebookBtn = (Button) findViewById(R.id.facebookBtn);
        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebook();
            }
        });
    }

    public void urlAmazon() {// @reference Stack Overflow URL & @authors
//        Snackbar.make(findViewById(android.R.id.content), "Opening browser...", Snackbar.LENGTH_LONG).show(); //Not displaying
        gotToUrl("https://www.amazon.co.uk/Pro-Acoustic-Foam-Wedge-Treatment/dp/B006I1J25E/");
    }

    public void gotToUrl(String url) { // @reference Stack Overflow URL & @authors
        Uri uriUrl = Uri.parse(url);
        Intent lauchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(lauchBrowser);
    }

    public void openFacebook() { //@reference StackOverflow FB & @authors
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1688960761404367"));
            startActivity(intent);
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/MrAudioApp")));
        }
    }

    public void startOver(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Sorry, you can't go back here", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            Toast.makeText(getApplicationContext(), "Nuh-uh, no increasing the volume! My ears hurt already!!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            Toast.makeText(getApplicationContext(), "What?! You want to decrease the volume?! I can barely hear anything!", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return false;
        }
    }
}
