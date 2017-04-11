package com.example.colin.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import android.content.Intent;
public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final EditText etUserName;

        //This line of code connects this java file to the xml file of the same name
        setContentView(R.layout.activity_user_area);

        //these connect to the buttons within that xml file
        etUserName = (EditText) findViewById(R.id.etUserName);

        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);

        /*
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");


        String message = name + "welcome to your user area";
        welcomeMessage.setText(message);
        etUserName.setText(username);
        */

    }
}
