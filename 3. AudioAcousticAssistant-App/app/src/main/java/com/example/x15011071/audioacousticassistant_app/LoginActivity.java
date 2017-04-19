package com.example.x15011071.audioacousticassistant_app;

/*
* @filename LoginActivity.java
* @author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
* @reference Stack Overflow URL - https://stackoverflow.com/questions/5026349/how-to-open-a-website-when-a-button-is-clicked-in-android-application
* @reference Stack Overflow Disable back button - https://stackoverflow.com/questions/4779954/disable-back-button-in-android
* @reference YouTube - TonikamiTV/Login Register 6 part series - https://www.youtube.com/watch?v=QxffHgiJ64M
* @date 11 April 2017

 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button termsLinkBtn;
    Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This line of code connects this java file to the xml file of the same name
        setContentView(R.layout.activity_login);

        //these connect to the text fields/buttons within that xml file
        etEmail = (EditText) findViewById(R.id.etEmail);//@reference Yotube - TonikamiTv/Login Register 6 part series & @authors
        etPassword = (EditText) findViewById(R.id.etPassword);//@reference Yotube - TonikamiTv/Login Register 6 part series & @authors

        termsLinkBtn = (Button)findViewById(R.id.termsLinkBtn); //@authors
        termsLinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTermsLink();
            }
        });

        bLogin = (Button) findViewById(R.id.bLogin);

        //This was code for a link to the register page on the login page in case someone clicked on login without having registered
        //however, we decided to take this out, but the code is still here.

//        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);


        //This connects the "Register Here" button on the login page to send the user to the Reigster page.
//        registerLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent registerIntent = new Intent (LoginActivity.this, RegisterActivity.class);
//                LoginActivity.this.startActivity(registerIntent);
//            }
//        });


        //This is the listener, it listens for when the Login button is clicked
        //When it is clicked it'll take the strings from the email text box and password textbox.(EditText)
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString();//@reference Yotube - TonikamiTv/Login Register 6 part series & @authors
                final String password = etPassword.getText().toString();//@reference Yotube - TonikamiTv/Login Register 6 part series & @authors

                //this is the response listener, This is listening for a response from the php file.(Login.php)
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //The response listener is waiting for a response from the php files
                        //This response is the boolean "success"
                        //This is done through Json, because Json can be used in both Java and Php
                        //@reference Yotube - TonikamiTv/Login Register 6 part series & @authors
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            //@authors
                            if (success) {


                                Intent intent = new Intent(LoginActivity.this, ChooseActivity.class);


                                LoginActivity.this.startActivity(intent);


                            } else {
                                //If the app doesn't get the "success" response from the php files, do this.
                                //This displays an alert box to the user displaying "Login Failed".
                                //@reference Yotube - TonikamiTv/Login Register 6 part series & @authors
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }
        });
    }

    public void openTermsLink(){ // @reference Stack Overflow URL & @authors
        gotToUrl("https://www.tumblr.com/policy/en/terms-of-service");
    }

    public void gotToUrl(String url) { // @reference Stack Overflow URL & @authors
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }


//    @Override //disables back button.
//    public void onBackPressed() { //@reference Stack Overflow Disable back button & @authors
//        Toast.makeText(getApplicationContext(),"Sorry, you can't go back here",Toast.LENGTH_LONG).show();
//    }
}
