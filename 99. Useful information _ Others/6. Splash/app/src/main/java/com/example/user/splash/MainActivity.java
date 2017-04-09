package com.example.user.splash;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import static com.example.user.splash.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(activity_main);

        Button advanceToCategories = (Button) findViewById(R.id.button2);
        advanceToCategories.setOnClickListener(new View.OnClickListener()){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, categories.class);
                startActivity(intent);
            }
        };
    }

    }

