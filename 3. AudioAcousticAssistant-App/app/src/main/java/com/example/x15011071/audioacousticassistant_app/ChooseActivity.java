package com.example.x15011071.audioacousticassistant_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        // Capture our button from layout
        Button button = (Button)findViewById(R.id.corky);
        // Register the onClick listener with the implementation above
        button.setOnClickListener(mCorkyListener);
    }


    public void Next(View view)
    {
        Intent intent = new Intent(this,RoomActivity.class);
        startActivity(intent);

    }
    private OnClickListener mCorkyListener = new OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
        }
    };


    public boolean YouTuber (){

    }
}
