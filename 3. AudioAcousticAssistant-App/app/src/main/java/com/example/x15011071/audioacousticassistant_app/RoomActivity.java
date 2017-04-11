package com.example.x15011071.audioacousticassistant_app;


        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
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

    final double BED = 40;

    double widthVal, heightVal, lengthVal, bedroomResult, emptyRoomResult;

    Boolean feet = true;
    Boolean metres = false; //default starts with feet = true;

    ChooseActivity ca = new ChooseActivity();

    Boolean youtubeRA = ca.youtube;
    Boolean homeArtistRA = ca.homeArtist;
    Boolean eventRA = ca.event;
    double catResultRA = ca.catResult;

//    TextView tv_result;
//    TextView tv_result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        roomVolumeResultTV = (TextView) findViewById(R.id.roomVolumeResultTV);

        emptyRoom = (Button)findViewById(R.id.emptyRoomBtn);
        emptyRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((youtubeRA = true) || (homeArtistRA = true) || (eventRA = true)){
                    emptyRoomResult = catResultRA;
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
                if((youtubeRA = true) || (homeArtistRA = true) || (eventRA = true)){
                    bedroomResult = catResultRA + BED;
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
    private void makeCalc(){
     //   final double FEET_CONVERSION = 30.48; // final value not working
        DecimalFormat df = new DecimalFormat("##.00");

        roomVolumeResultTV.setText("");

        if(feet = true){
            double widthFeet = Double.valueOf(widthET.getText().toString());
            double heightFeet = Double.valueOf(heightET.getText().toString());
            double lengthFeet = Double.valueOf(lengthET.getText().toString());

            double totalFeet = widthFeet * heightFeet * lengthFeet; //result is in metres
            double convertToMetres = totalFeet * 30.48; //30.48 is the conversion rate for converting FEET into METRES (squared)

            String roundedMetres = df.format(totalFeet);
            String roundedFeet = df.format(convertToMetres);

            roomVolumeResultTV.setText(roundedFeet + " sq. feet" + "\n" + roundedMetres + " sq. metres");
        }

        else if(metres = true){
            double widthFeet = Double.valueOf(widthET.getText().toString());
            double heightFeet = Double.valueOf(heightET.getText().toString());
            double lengthFeet = Double.valueOf(lengthET.getText().toString());

            double totalMetres = widthFeet * heightFeet * lengthFeet; //result is in metres
            double convertToFeet = totalMetres * 32.80; //32.80 is the conversion rate for converting METRES to FEET (squared)

            String roundedMetres = df.format(totalMetres);
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
        startActivity(intent);
    }
}
