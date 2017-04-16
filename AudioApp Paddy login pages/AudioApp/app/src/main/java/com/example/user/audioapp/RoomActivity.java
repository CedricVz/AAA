package com.example.user.audioapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.user.audioapp.R;
import com.example.user.audioapp.RecordActivity;


public class RoomActivity extends AppCompatActivity {
    TextView input1;
    TextView input2;
    TextView input3;

    //    TextView tv_result;
//    TextView tv_result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_room);
        Button y = (Button)this.findViewById(R.id.btnGo);
        y.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(RoomActivity.this, RecordActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in_two, R.anim.fade_out_two);
            }
        });

        input1 = (EditText) findViewById(R.id.et_input1);
        input2 = (EditText) findViewById(R.id.et_input2);
        input3 = (EditText) findViewById(R.id.et_input3);

        Button bt_calculate1;
        bt_calculate1 = (Button) findViewById(R.id.bt_calculate1);

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

        double n1 = Double.valueOf(input1.getText().toString());
        double n2 = Double.valueOf(input2.getText().toString());
        double n3 = Double.valueOf(input3.getText().toString());


        double result= n1*n2*n3;
        double conversion = result*35.31;

//        tv_result.setText("The result is: " + result);
//        tv_result2.setText("The result is: " + conversion);



    }
    public void calculateRoom2(View v){}
    private void makeCalculations2() {

        double n1 = Double.valueOf(input1.getText().toString());
        double n2 = Double.valueOf(input2.getText().toString());
        double n3 = Double.valueOf(input3.getText().toString());


        double conversion= n1*n2*n3;

//        tv_result.setText("The result is: " + result);
//        tv_result2.setText("The result is: " + conversion);



    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        Button resetButton=(Button)findViewById(R.id.bt_calculate1);
        Button resetButton2=(Button)findViewById(R.id.bt_calculate2);
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
    public void record_page(View view)
    {
        Intent intent = new Intent(this,RecordActivity.class);
        startActivity(intent);
    }
}
