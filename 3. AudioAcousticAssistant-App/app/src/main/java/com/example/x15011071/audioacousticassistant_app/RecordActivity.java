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
        String button_text;
        button_text=((Button)view).getText().toString();
        if(button_text.equals("Next"))
        {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        else
        {

        }
    }
}
