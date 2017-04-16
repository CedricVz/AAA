package com.example.x15011071.audioacousticassistant_app;

/*
* @filename RoomActivity.java
* @author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
* @reference Stack Overflow SetIntent - https://stackoverflow.com/questions/6751564/how-to-pass-a-boolean-between-intents
* @date 11 April 2017
*
*
 */

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.KeyEvent;
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
    String roundedMetres;

    double widthVal, heightVal, lengthVal, bedroomResult, emptyRoomResult;

    Boolean feet = true;
    Boolean metres = false; //default starts with feet = true

//    TextView tv_result;
//    TextView tv_result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        //to get values from ChooseActivity.java
        final boolean youtube = getIntent().getExtras().getBoolean("youtube");
        final boolean homeArtist = getIntent().getExtras().getBoolean("homeArtist");
        final boolean event = getIntent().getExtras().getBoolean("event");

        Bundle b = getIntent().getExtras();
        final double catResult = b.getDouble("catResult");

        roomVolumeResultTV = (TextView) findViewById(R.id.roomVolumeResultTV);

        youtubeRA = youtube;
        homeArtistRA = homeArtist;
        eventRA = event;


        emptyRoom = (Button)findViewById(R.id.emptyRoomBtn);
        emptyRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            public void onClick(View v) {
                if((youtube == true) || (homeArtist == true) || (event == true)){
                    bedroomResult = catResult + BED;
                    next();

                }else{
                    Toast.makeText(getApplicationContext(),"Sorry, sn error occurred. Please try again",Toast.LENGTH_SHORT).show();
                }
            }
        });

        widthET = (EditText) findViewById(R.id.widthET);
        lengthET = (EditText) findViewById(R.id.lengthET);
        heightET = (EditText) findViewById(R.id.heightET);

        Button bt_calculate1;
        bt_calculate1 = (Button) findViewById(R.id.calcBtn);


        bt_calculate1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                makeCalc();
            }
        });
    }
//    public void fets(View view){
//        TextView textView = (TextView) findViewById(R.id.tv_result2);
//        textView.setVisibility(View.VISIBLE);
//        TextView textView2 = (TextView) findViewById(R.id.tv_result);
//        textView2.setVisibility(View.INVISIBLE);
//        }
//    public void mets(View view){
//        TextView textView = (TextView) findViewById(R.id.tv_result);
//        textView.setVisibility(View.VISIBLE);
//        TextView textView2 = (TextView) findViewById(R.id.tv_result2);
//        textView2.setVisibility(View.INVISIBLE);
//    }


    public void feetCalc(View v){}
    public void makeCalc(){
     //   final double FEET_CONVERSION = 30.48; // final value not working
        DecimalFormat df = new DecimalFormat("##.00");

        roomVolumeResultTV.setText("");

        if(feet == true){
            double widthFeet = Double.valueOf(widthET.getText().toString());
            double heightFeet = Double.valueOf(heightET.getText().toString());
            double lengthFeet = Double.valueOf(lengthET.getText().toString());

            double totalFeet = widthFeet * heightFeet * lengthFeet; //result is in metres
            double convertToMetres = totalFeet * 30.48; //30.48 is the conversion rate for converting FEET into METRES (squared)

            roundedMetres = df.format(totalFeet);
            String roundedFeet = df.format(convertToMetres);

            roomVolumeResultTV.setText(roundedFeet + " sq. feet" + "\n" + roundedMetres + " sq. metres");
        }

        else if(metres == true){
            double widthFeet = Double.valueOf(widthET.getText().toString());
            double heightFeet = Double.valueOf(heightET.getText().toString());
            double lengthFeet = Double.valueOf(lengthET.getText().toString());

            double totalMetres = widthFeet * heightFeet * lengthFeet; //result is in metres
            double convertToFeet = totalMetres * 32.80; //32.80 is the conversion rate for converting METRES to FEET (squared)

            roundedMetres = df.format(totalMetres);
            String roundedFeet = df.format(convertToFeet);

            roomVolumeResultTV.setText(roundedMetres + " sq. metres" + "\n" + roundedFeet + " sq. feet");
        }

        else{
            Toast.makeText(getApplicationContext(),"Sorry, an error occurred. Please try again",Toast.LENGTH_SHORT).show(); //error catching
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        calcBtn = (Button)findViewById(R.id.calcBtn);
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
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
    public void next()
    {
        Intent intent = new Intent(this,RecordActivity.class);

        intent.putExtra("youtubeRA", youtubeRA);
        intent.putExtra("eventRA", eventRA);
        intent.putExtra("homeArtistRA", homeArtistRA);

//        Bundle c = new Bundle();
//        c.putDouble("roundedMetres", Double.parseDouble(roundedMetres));
//        intent.putExtras(c);
//
//        Bundle d = new Bundle();
//        d.putDouble("emptyRoomResult", emptyRoomResult);
//        intent.putExtras(d);
//
//        Bundle e = new Bundle();
//        e.putDouble("bedroomResult", bedroomResult);
//        intent.putExtras(e);

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Sorry, you can't go back here",Toast.LENGTH_LONG).show();
    }


}
