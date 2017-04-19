package com.example.x15011071.audioacousticassistant_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChooseActivity extends AppCompatActivity {
    //final EditText etUserName;



    //these connect to the buttons within that xml file
   // etUserName = (EditText) findViewById(R.id.etUserName);

//        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);

        /*
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");


        String message = name + "welcome to your user area";
        welcomeMessage.setText(message);
        etUserName.setText(username);
        */

    public final double GREENSCREEN = 1.5;
    public final double CHAIR = 1.9;
    public final double PERSON = 4.73;

    public Button youtubeBtn, homeArtistBtn, eventBtn;
    public Boolean youtube = false;
    public Boolean homeArtist = false;
    public Boolean event = false;
    public double catResult;

    public final double youtubeResult = ((GREENSCREEN + CHAIR) + PERSON);
    public final double homeArtistResult = CHAIR + PERSON;
    public final double eventResult = CHAIR * 2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);



        youtubeBtn = (Button) findViewById(R.id.youtubeBtn);
        youtubeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                catResult = youtubeResult; //catResult not working
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

                catResult = homeArtistResult;
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

                catResult = eventResult;
                event = true;

                youtube = false;
                homeArtist = false;
                next();
            }
        });
    }

    public void next() {
        Intent intent = new Intent(this,RoomActivity.class);

        intent.putExtra("youtube", youtube);
        intent.putExtra("event", event);
        intent.putExtra("homeArtist", homeArtist);

        Bundle b = new Bundle();
        b.putDouble("catResult", catResult);
        intent.putExtras(b);

        startActivity(intent);

    }

//    @Override //disables back button.
//    public void onBackPressed() { //@reference Stack Overflow Disable back button & @authors
//        Toast.makeText(getApplicationContext(),"Sorry, you can't go back here",Toast.LENGTH_LONG).show();
//    }

}
