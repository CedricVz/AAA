package com.example.x15011071.audioacousticassistant_app;

/*
* @filename RegisterActivity.java
* @author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
* @reference YouTube - TonikamiTV/Login Register 6 part series - https://www.youtube.com/watch?v=QxffHgiJ64M
* @reference Stack Overflow URL - https://stackoverflow.com/questions/5026349/how-to-open-a-website-when-a-button-is-clicked-in-android-application
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

public class RegisterActivity extends AppCompatActivity  {

    //Variables being declared, they were declared further down but this was causing issues
    EditText etEmail, etPassword;
    Button bRegister, termsLinkBtn;
    String email, password;
    RequestQueue queue;
    RegisterRequest registerRequest;

    @Override
    //Connects this page to the corresponding xml
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        termsLinkBtn = (Button) findViewById(R.id.termsLinkBtn);
        termsLinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTermsLink();
            }
        });

        bRegister = (Button) findViewById(R.id.bRegister);

        //this connects the register button to send the data in the fields above to the RegisterRequest.java class.
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //this is getting the values that the user has placed into the text areas and sending them to the RegisterRequest.java class.

                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                //The response listener is waiting for a response from the php files
                //This response is the boolean "success"
                //This is done through Json, because Json can be used in both Java and Php
                //@reference Yotube - TonikamiTv/Login Register 6 part series & @authors
                Response.Listener<String> responseListener = new Response.Listener<String>() {


                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            //if the registration is successful (recieves "success" from the register.php file), It will send the user to the login page.
                            //@authors
                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                                overridePendingTransition(R.anim.fade_in_two, R.anim.fade_out_two);
                                //else, display an error message ("Register Failed") and allow the user to try again (.setNegativeButton("Retry", null).
                                //@reference Yotube - TonikamiTv/Login Register 6 part series & @authors
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed, Email may be taken.")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                };

                //this is the code that connects the information above and sends it to the RegisterRequest.java class.
                //@reference Yotube - TonikamiTv/Login Register 6 part series & @authors
                registerRequest = new RegisterRequest(email, password, responseListener);

                queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }


        });
    }

    //this sends the user from the register straight into the app
    //This isn't used because the user is sent to the login page to login
  /*  }
    public void Next(View view)
    {
        Intent intent = new Intent(this,ChooseActivity.class);
        startActivity(intent);
    }*/

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