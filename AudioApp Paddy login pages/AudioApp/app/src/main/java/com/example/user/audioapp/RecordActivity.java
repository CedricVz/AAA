package com.example.user.audioapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_record);
        Button z = (Button)this.findViewById(R.id.nextBtn);
        z.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent j = new Intent(RecordActivity.this, AdviceActivity.class);
                startActivity(j);
                overridePendingTransition(R.anim.fade_in_two, R.anim.fade_out_two);
            }
        });
    }
    public void advice_page(View view)
    {
        Intent intent = new Intent(this,AdviceActivity.class);
        startActivity(intent);
    }
}
