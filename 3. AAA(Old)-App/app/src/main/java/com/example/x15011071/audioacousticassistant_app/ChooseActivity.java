package com.example.x15011071.audioacousticassistant_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {
    private double chromeScreen = 1.9;
    private double chair = 1.9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    if (){

    }

    public void Next(View view)
    {
        Intent intent = new Intent(this,RoomActivity.class);
        startActivity(intent);


    }
}
