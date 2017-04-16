package com.example.user.audioapp;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    LoginButton loginButton;
    CallbackManager callbackManager;
    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    public static final int SIGN_IN_CODE = 777;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build();

        signInButton = (SignInButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener(){
          @Override
            public void onClick(View v){
              Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
              startActivityForResult(intent, SIGN_IN_CODE);
          }
        });

        FacebookSdk.sdkInitialize(getApplicationContext());
        //loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this, loginResult.getAccessToken().getUserId(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Login cancelled", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        Button b = (Button)this.findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    Intent f = new Intent(MainActivity.this, SignUpActivity.class);
                    startActivity(f);
                    overridePendingTransition(R.anim.fade_in_two, R.anim.fade_out_two);
                }
            });
        Button a = (Button)this.findViewById(R.id.sign);
        a.setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View v){
                Intent a = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(a);
                overridePendingTransition(R.anim.fade_in_two, R.anim.fade_out_two);
            }
        });
        }

    public void sign_in_page(View view){
        Intent startNewActivity = new Intent(this, LoginActivity.class);
        startActivity(startNewActivity);

    }
    public void sign_up_page(View view){
        Intent startNewActivity = new Intent(this, SignUpActivity.class);
        startActivity(startNewActivity);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 777){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()){
            goMainScreen();
        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, ChooseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
