package com.example.x15011071.audioacousticassistant_app;


/*
* @filename AdviceActivity.java
* @author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
* @reference Stack Overflow URL - https://stackoverflow.com/questions/5026349/how-to-open-a-website-when-a-button-is-clicked-in-android-application
* @reference StackOverflow FB - https://stackoverflow.com/questions/4810803/open-facebook-page-from-android-app
* @reference Stack Overflow SetIntent - https://stackoverflow.com/questions/6751564/how-to-pass-a-boolean-between-intents
* @date 11 April 2017

 */

import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;


public class AdviceActivity extends AppCompatActivity {

    Button urlBtn, facebookBtn, startOverBtn;
    TextView changingText;
    ImageView imageIMG;

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

        imageIMG = (ImageView)findViewById(R.id.imageIMG);

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
            changingText.setText("As a YouTuber, you will need some carpet, greenscreen and " + youtubeAF + " square metres of foam. You can but the kit by clicking the image below.");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                imageIMG.setImageDrawable(getResources().getDrawable(R.drawable.youtubefoam, getApplicationContext().getTheme()));
//            } else {
//                imageIMG.setImageDrawable(getResources().getDrawable(R.drawable.youtubefoam));
//            }
//
// changing image not working per result

        } else if (homeArtist == true) {
            changingText.setText("As a YouTuber, you will need some carpet " + youtubeAF + " square metres of foam. You can but the kit by clicking the image below.");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                imageIMG.setImageDrawable(getResources().getDrawable(R.drawable.foamhomeartist, getApplicationContext().getTheme()));
//            } else {
//                imageIMG.setImageDrawable(getResources().getDrawable(R.drawable.foamhomeartist));
//            }
        } else if (event == true) {
            changingText.setText("As a YouTuber, you will need " + youtubeAF + " square metres of foam. You can but the kit by clicking the image below.");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                imageIMG.setImageDrawable(getResources().getDrawable(R.drawable.foamevent, getApplicationContext().getTheme()));
//            } else {
//                imageIMG.setImageDrawable(getResources().getDrawable(R.drawable.foamevent));
//            }

        }

        urlBtn = (Button) findViewById(R.id.urlBTN);
        urlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (youtube == true){
                    urlAmazonYouTubeKit();

                }
            else if (homeArtist == true){
                urlAmazonHomeArtistKit();
                }

            else if (event == true) {
                    urlAmazonEventKit();
                }
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

    public void urlAmazonYouTubeKit() {// @reference Stack Overflow URL & @authors
//        Snackbar.make(findViewById(android.R.id.content), "Opening browser...", Snackbar.LENGTH_LONG).show(); //Not displaying
        gotToUrl("https://www.amazon.com/Auralex-Acoustics-Roominator-Absorption-Treatment/dp/B000E1U9ZG");
    }

    public void urlAmazonHomeArtistKit() {// @reference Stack Overflow URL & @authors
//        Snackbar.make(findViewById(android.R.id.content), "Opening browser...", Snackbar.LENGTH_LONG).show(); //Not displaying
        gotToUrl("https://www.amazon.com/Soundproof-Store-Acoustic-Soundproofing-Charcoal/dp/B00ATP5KF6");
    }

    public void urlAmazonEventKit() {// @reference Stack Overflow URL & @authors
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

//    @Override
//    public void onBackPressed() {
//        Toast.makeText(getApplicationContext(), "Sorry, you can't go back here", Toast.LENGTH_LONG).show();
//    } //not working separately with onKeyDown(), instead used in onKeyDown().

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            Toast.makeText(getApplicationContext(), "Nuh-uh, no increasing the volume! My ears hurt already!!", Toast.LENGTH_LONG).show();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            Toast.makeText(getApplicationContext(), "What?! You want to decrease the volume?! I can barely hear anything!", Toast.LENGTH_LONG).show();
            return true;
        }
        else if(keyCode == KeyEvent.KEYCODE_BACK){ //KEYCODE_BACK is the Back Button.
            Toast.makeText(getApplicationContext(), "Sorry, you can't go back here", Toast.LENGTH_LONG).show();
            return true;
        }
        else{
            return false;
        }
    }
}
