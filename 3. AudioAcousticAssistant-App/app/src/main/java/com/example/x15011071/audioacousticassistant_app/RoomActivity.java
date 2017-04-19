package com.example.x15011071.audioacousticassistant_app;

/*
* @filename RoomActivity.java
* @author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
* @reference Stack Overflow SetIntent - https://stackoverflow.com/questions/6751564/how-to-pass-a-boolean-between-intents
* @reference Stack Overflow Disable back button - https://stackoverflow.com/questions/4779954/disable-back-button-in-android
* @date 11 April 2017
*
 */

        import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;



public class RoomActivity extends AppCompatActivity {

    TextView widthET, heightET, lengthET, roomVolumeResultTV;
    Button calcBtn, emptyRoom, bedroom;

    public boolean youtubeRA, homeArtistRA, eventRA;

    final double BED = 40;
    double totalFeet, convertToMetres, totalMetres, convertToFeet;
    String roundedMetres, roundedFeet;

    double bedroomResult, emptyRoomResult;

    Boolean feet = true;
    Boolean metres = false; //default starts with feet = true

//    TextView tv_result;
//    TextView tv_result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        //to get values from ChooseActivity.java
        final boolean youtube = getIntent().getExtras().getBoolean("youtube");//@reference Stack Overflow SetIntent & @authors
        final boolean homeArtist = getIntent().getExtras().getBoolean("homeArtist");//@reference Stack Overflow SetIntent & @authors
        final boolean event = getIntent().getExtras().getBoolean("event");//@reference Stack Overflow SetIntent & @authors
        Bundle b = getIntent().getExtras();//@reference Stack Overflow SetIntent & @authors
        final double catResult = b.getDouble("catResult");//@reference Stack Overflow SetIntent & @authors

        roomVolumeResultTV = (TextView) findViewById(R.id.roomVolumeResultTV);

        youtubeRA = youtube;
        homeArtistRA = homeArtist;
        eventRA = event; //assigning values from previous intent to this one



        //User selects Bedroom or Empty Room, values change accordingly. If/else used to catch errors.
        emptyRoom = (Button)findViewById(R.id.emptyRoomBtn);
        emptyRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//@authors

                if((youtube == true) || (homeArtist == true) || (event == true)){
                    emptyRoomResult = catResult;
                    next();

                }else{
                    Toast.makeText(getApplicationContext(),"Sorry, sn error occurred. Please try again",Toast.LENGTH_SHORT).show();
                }


            }
        });

        bedroom = (Button)findViewById(R.id.bedroomBtn);
        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//@authors
                if((youtube == true) || (homeArtist == true) || (event == true)){
                    bedroomResult = catResult + BED;
                    next();

                }else{
                    Toast.makeText(getApplicationContext(),"Sorry, sn error occurred. Please try again",Toast.LENGTH_SHORT).show();
                }
            }
        });



        //Calculator, declaring where to get values from
        widthET = (EditText) findViewById(R.id.widthET);//@authors
        lengthET = (EditText) findViewById(R.id.lengthET);//@authors
        heightET = (EditText) findViewById(R.id.heightET);//@authors


        calcBtn = (Button) findViewById(R.id.calcBtn);
        calcBtn.setOnClickListener(new View.OnClickListener() {//@authors

            public void onClick(View view) {
                makeCalc();
            }
        });
    }


    public void makeCalc(){//@authors
     //   final double FEET_CONVERSION = 30.48; // final value not working

        if(!(widthET.getText().toString().equals("") || (heightET.getText().toString().equals("") || (lengthET.getText().toString().equals(""))))) { //this is checking if something is typed into the width, height and length fields, if not empty, it calculates. If empty, prints message.


            DecimalFormat df = new DecimalFormat("##.00");//used to round figures

            roomVolumeResultTV.setText(""); //sets message to empty on start calculation

            if (feet == true) { // used if feet radio button selected
                double widthFeet = Double.valueOf(widthET.getText().toString());
                double heightFeet = Double.valueOf(heightET.getText().toString());
                double lengthFeet = Double.valueOf(lengthET.getText().toString()); //getting values from input, converting to String, then double

                totalFeet = widthFeet * heightFeet * lengthFeet; //result is in metres
                convertToMetres = totalFeet * 30.48; //30.48 is the conversion rate for converting FEET into METRES (squared)

                roundedMetres = df.format(totalFeet);
                roundedFeet = df.format(convertToMetres); //rounding to 2 decimal places

                roomVolumeResultTV.setText(roundedFeet + " sq. feet" + "\n" + roundedMetres + " sq. metres"); //prints both values to screen
            }
            else if (metres == true) { // used if metres radio button selected
                double widthFeet = Double.valueOf(widthET.getText().toString());
                double heightFeet = Double.valueOf(heightET.getText().toString());
                double lengthFeet = Double.valueOf(lengthET.getText().toString()); //getting values from input, converting to String, then double

                totalMetres = widthFeet * heightFeet * lengthFeet; //result is in metres
                convertToFeet = totalMetres * 32.80; //32.80 is the conversion rate for converting METRES to FEET (squared)

                roundedMetres = df.format(totalMetres);
                roundedFeet = df.format(convertToFeet);//rounding to 2 decimal places

                roomVolumeResultTV.setText(roundedMetres + " sq. metres" + "\n" + roundedFeet + " sq. feet"); //prints both values to screen
            } else {
                Toast.makeText(getApplicationContext(), "Sorry, an error occurred. Please try again", Toast.LENGTH_SHORT).show(); //error catching
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Please enter value into all fields",Toast.LENGTH_LONG).show(); //if values are empty
        }
    }


    //Radio button for selecting feet or metres. Final values that is used in metres.
    public void onRadioButtonClicked(View view) {//@authors
        // Is the button now checked?
        calcBtn = (Button)findViewById(R.id.calcBtn);
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {//@authors
            case R.id.radio_Meters:
                if (checked)
                    calcBtn.setText("CALCULATE IN METRES");
                    metres = true;
                    feet = false;
                    break;
            case R.id.radio_Feet:
                if (checked)
                    calcBtn.setText("CALCULATE IN FEET");
                    metres = false;
                    feet = true;
                    break; //Depending on what radio button is checked, text on button will change accordingly.
            default:
                roomVolumeResultTV.setText("Select feet or metres");
        }
    }



    public void next(){ // to go to the next intent
        Intent intent = new Intent(this,RecordActivity.class);


        //This is to post values to next intent.
        intent.putExtra("youtubeRA", youtubeRA);//@reference Stack Overflow SetIntent & @authors
        intent.putExtra("eventRA", eventRA);//@reference Stack Overflow SetIntent & @authors
        intent.putExtra("homeArtistRA", homeArtistRA);//@reference Stack Overflow SetIntent & @authors

//******** Was trying to push more than one double value to next intent, not working. *One* Bundle with *one* value works only
//        Bundle c = new Bundle();//@reference Stack Overflow SetIntent & @authors
//        c.putDouble("roundedMetres", Double.parseDouble(roundedMetres));//@reference Stack Overflow SetIntent & @authors
//        intent.putExtras(c);//@reference Stack Overflow SetIntent & @authors//@reference Stack Overflow SetIntent & @authors
//
//        Bundle d = new Bundle();//@reference Stack Overflow SetIntent & @authors
//        d.putDouble("emptyRoomResult", emptyRoomResult);//@reference Stack Overflow SetIntent & @authors
//        intent.putExtras(d);//@reference Stack Overflow SetIntent & @authors
//
//        Bundle e = new Bundle();//@reference Stack Overflow SetIntent & @authors
//        e.putDouble("bedroomResult", bedroomResult);//@reference Stack Overflow SetIntent & @authors
//        intent.putExtras(e);//@reference Stack Overflow SetIntent & @authors

        startActivity(intent); //starts next intent.
        overridePendingTransition(R.anim.fade_in_two, R.anim.fade_out_two);
    }

    @Override //disables back button.
    public void onBackPressed() { //@reference Stack Overflow Disable back button & @authors
        Toast.makeText(getApplicationContext(),"Sorry, you can't go back here",Toast.LENGTH_LONG).show();
    }


}
