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


public class RoomActivity extends AppCompatActivity {
    TextView width;
    TextView height;
    TextView length;

    double widthVal, heightVal, lengthVal;

    ChooseActivity ca = new ChooseActivity();

    Boolean youtubeRA = ca.youtube;
    Boolean homeArtistRA = ca.homeArtist;
    Boolean eventRA = ca.event;
    double catResultRA = ca.catResult;

    final double BED = 40;
    double bedroomResult, emptyRoomResult;

    Button emptyRoom, bedroom;



//    TextView tv_result;
//    TextView tv_result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

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

        width = (EditText) findViewById(R.id.widthET);
        length = (EditText) findViewById(R.id.lengthET);
        height = (EditText) findViewById(R.id.heightET);

        Button bt_calculate1;
        bt_calculate1 = (Button) findViewById(R.id.calcMetBtn);

//        tv_result = (TextView) findViewById(R.id.tv_result);
//        tv_result2 = (TextView) findViewById(R.id.tv_result2);

        bt_calculate1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                makeCalculations1();
                makeCalculations2();
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


    public void calculateRoom(View v){}
    private void makeCalculations1(){

        widthVal = Double.valueOf(width.getText().toString()); ///PLEASE FIX FEARGHAL!
        heightVal = Double.valueOf(height.getText().toString());
        lengthVal = Double.valueOf(length.getText().toString());


        double result= widthVal * heightVal * lengthVal;
        double conversion = result*35.31;

//        tv_result.setText("The result is: " + result);
//        tv_result2.setText("The result is: " + conversion);



    }
    public void calculateRoom2(View v){}
    private void makeCalculations2() {

        widthVal = Double.valueOf(width.getText().toString()); ///PLEASE FIX FEARGHAL!
        heightVal = Double.valueOf(height.getText().toString());
        lengthVal = Double.valueOf(length.getText().toString());


        double converion = widthVal * heightVal * lengthVal;

//        tv_result.setText("The result is: " + result);
//        tv_result2.setText("The result is: " + conversion);



    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        Button resetButton=(Button)findViewById(R.id.calcMetBtn);
        Button resetButton2=(Button)findViewById(R.id.calcFeetBtn);
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_Meters:
                if (checked)
                    resetButton.setVisibility(View.VISIBLE);
                    resetButton2.setVisibility(View.INVISIBLE);
                    break;
            case R.id.radio_Feet:
                if (checked)
                    resetButton2.setVisibility(View.VISIBLE);
                    resetButton.setVisibility(View.INVISIBLE);
                    break;
        }
    }
    public void next()
    {
        Intent intent = new Intent(this,RecordActivity.class);
        startActivity(intent);
    }
}
