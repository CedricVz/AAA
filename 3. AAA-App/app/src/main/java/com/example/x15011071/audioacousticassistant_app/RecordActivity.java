package com.example.x15011071.audioacousticassistant_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
    }
    public void Next(View view)
    {
        Intent intent = new Intent(this,AdviceActivity.class);
        startActivity(intent);
    }
}
