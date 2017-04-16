package com.example.user.audioapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choose);
        Button u = (Button)this.findViewById(R.id.TubeBtn);
        u.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent b = new Intent(ChooseActivity.this, RoomActivity.class);
                startActivity(b);
                overridePendingTransition(R.anim.fade_in_two, R.anim.fade_out_two);
            }
        });

        Button q = (Button)this.findViewById(R.id.artistBtn);
        q.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent c = new Intent(ChooseActivity.this, RoomActivity.class);
                startActivity(c);
                overridePendingTransition(R.anim.fade_in_two, R.anim.fade_out_two);
            }
        });

        Button w = (Button)this.findViewById(R.id.EOBtn);
        w.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent d = new Intent(ChooseActivity.this, RoomActivity.class);
                startActivity(d);
                overridePendingTransition(R.anim.fade_in_two, R.anim.fade_out_two);
            }
        });
    }
    public void room_page(View view){
        Intent startNewActivity = new Intent(this, RoomActivity.class);
        startActivity(startNewActivity);
    }
}
