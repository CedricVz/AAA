
package com.example.x15011071.audioacousticassistant_app;

/*
* @filename MainActivity.java
* @author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
* @reference Stack Overflow Disable back button - https://stackoverflow.com/questions/4779954/disable-back-button-in-android
* @date 11 April 2017

 */

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void NextLogin(View view)
    {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    public void NextRegister(View view)
    {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    public void NextContinue(View view)
    {
        Intent intent = new Intent(this,ChooseActivity.class);
        startActivity(intent);
    }

    @Override //disables back button.
    public void onBackPressed() { //@reference Stack Overflow Disable back button & @authors
        Toast.makeText(getApplicationContext(),"Sorry, you can't go back here",Toast.LENGTH_LONG).show();
    }
}