package com.example.x15011071.audioacousticassistant_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    final double GREENSCREEN = 1.5;
    final double CHAIR = 1.9;
    final double PERSON = 4.73;

    Button youtubeBtn, homeArtistBtn, eventBtn;
    Boolean youtube = false;
    Boolean homeArtist = false;
    Boolean event = false;
    double catResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);


        youtubeBtn = (Button) findViewById(R.id.youtubeBtn);
        youtubeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                catResult = GREENSCREEN + CHAIR + PERSON;
                youtube = true;

                homeArtist = false;
                event = false;
                next();
            }
        });

        homeArtistBtn = (Button) findViewById(R.id.homeArtistBtn);
        homeArtistBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                catResult = CHAIR + PERSON;
                homeArtist = true;

                youtube = false;
                event = false;
                next();
            }
        });

        eventBtn = (Button) findViewById(R.id.eventBtn);
        eventBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                catResult = PERSON * 2; // default no of people
                event = true;

                youtube = false;
                homeArtist = false;
                next();
            }
        });
    }



    public void next()
    {
        Intent intent = new Intent(this,RoomActivity.class);
        startActivity(intent);


    }
}
