
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

    //Navigation

    //this sends the user to the login page
    public void NextLogin(View view)
    {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_two, R.anim.fade_out_two);//This is what allows the pages to fade together instead of a hard cut
    }

    //this sends the user to the register page
    public void NextRegister(View view)
    {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_two, R.anim.fade_out_two);
    }

    //this sends the user to the choose page (used for debugging purposes)
    public void NextContinue(View view)
    {
        Intent intent = new Intent(this,ChooseActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_two, R.anim.fade_out_two);
    }

    @Override //disables back button.
    public void onBackPressed() { //@reference Stack Overflow Disable back button & @authors
        Toast.makeText(getApplicationContext(),"Sorry, you can't go back here",Toast.LENGTH_LONG).show();
    }
}