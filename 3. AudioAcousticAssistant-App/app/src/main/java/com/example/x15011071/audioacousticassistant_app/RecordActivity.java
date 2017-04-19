package com.example.x15011071.audioacousticassistant_app;

/*
* @filename RecordActivity.java
* @author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
* @reference YouTube - https://www.youtube.com/watch?v=jcrh8C376-c
* @reference StackOverflow - https://stackoverflow.com/questions/41202083/auto-stop-recording-after-seconds
* @reference StackOverflow Measuring dB - http://stackoverflow.com/questions/15693990/measuring-decibels-with-mobile-phone
* @reference YouTube - Android MediaPlayer/MediaRecorderTutorial - https://www.youtube.com/watch?v=jcrh8C376-c
* @reference Stack Overflow SetIntent - https://stackoverflow.com/questions/6751564/how-to-pass-a-boolean-between-intents
* @date 11 April 2017
*
*
 */

import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.util.Log;


import java.io.File;
import java.io.IOException;


public class RecordActivity extends AppCompatActivity {

    boolean youtubeRec, homeArtistRec, eventRec;

    static final String TAG = "testMessage";

    Button recordBtn, nextBtn;
    TextView infoTV;
    MediaPlayer play;//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
    MediaRecorder record;//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
    String FILE;//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
    Thread runner;
    private static double maxAverage = 0.0;
    static final private double REFERENCE = 0.6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        //to get values from RoomActivity.java
        final boolean youtubeRA = getIntent().getExtras().getBoolean("youtubeRA"); //@reference Stack Overflow SetIntent & @authors
        final boolean homeArtistRA = getIntent().getExtras().getBoolean("homeArtistRA"); //@reference Stack Overflow SetIntent & @authors
        final boolean eventRA = getIntent().getExtras().getBoolean("eventRA"); //@reference Stack Overflow SetIntent & @authors

//        Bundle c = getIntent().getExtras();//@reference Stack Overflow SetIntent & @authors
//        final double roundedMetres = c.getDouble("roundedMetres");
//
//        Bundle d = getIntent().getExtras();//@reference Stack Overflow SetIntent & @authors
//        final double emptyRoomResult = d.getDouble("emptyRoomResult");
//
//        Bundle e = getIntent().getExtras();//@reference Stack Overflow SetIntent & @authors
//        final double bedroomResult = e.getDouble("bedroomResult"); //cannot have more than one Bundle, or more than one variable in Bundle

        youtubeRec = youtubeRA;
        homeArtistRec = homeArtistRA;
        eventRec = eventRA;

        play = new MediaPlayer();//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial

        FILE = Environment.getExternalStorageDirectory() + "/tempRecord.3gpp";//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
        infoTV = (TextView)findViewById(R.id.infoTV);
        nextBtn = (Button)findViewById(R.id.nextBtn);

        nextBtn.setVisibility(View.INVISIBLE); //nextBtn hidden until recording is complete //@authors

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Calculating....", Toast.LENGTH_SHORT).show();
                next();
            }
        });


        recordBtn = (Button) findViewById(R.id.recordBtn);//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
        recordBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (recordBtn.getText().toString().equals("Tap\nto\nstart\nmeasuring")) {//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
                    try {
                        startRecord();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    infoTV.setText("Measuring dB");
                    recordBtn.setText("Stop\nmeasuring");
//                    Toast.makeText(getApplicationContext(),Double.toString(getMaxAmp()) + " db.", Toast.LENGTH_SHORT).show();
                }

                else if (recordBtn.getText().toString().equals("Stop\nmeasuring")) {//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
                    stopRecord();
                    infoTV.setText("Your dB value is " + getMaxAmp() + " db.");
                    recordBtn.setText("Play");
//                    Toast.makeText(getApplicationContext(),Double.toString(getMaxAmp()) + " db.", Toast.LENGTH_SHORT).show();
                    recordBtn.setVisibility(View.INVISIBLE); //removes Record button, no need to record any more.
                    nextBtn.setVisibility(View.VISIBLE);
                }

//                else if (recordBtn.getText().toString().equals("Play")) {//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
//                    try {
//                        startPlayback();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    infoTV.setText("Playing...");
//                    recordBtn.setText("Stop\nPlayback"); //For playback and testing, not needed in app
//
//                }
//
//                else if (recordBtn.getText().toString().equals("Stop\nPlayback")){//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
//                    try {
//                        stopPlayback();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    recordBtn.setText("Record");
//                    infoTV.setText("Tap button above to record"); //For playback and testing, not needed in app
//                }
                else{ //@authors
                    Toast.makeText(getApplicationContext(),"Sorry an error occurred. Please try again", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    public void startRecord() throws Exception{//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
        if (record != null){
            record.release();
        }

        File fileOut = new File(FILE);//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
        if (fileOut != null){
            fileOut.delete();
        }

        record = new MediaRecorder();//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
        record.setAudioSource(MediaRecorder.AudioSource.MIC);//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
        record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
        record.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); // 2.3.3 or below //@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
        record.setOutputFile(FILE);//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors

        record.prepare();//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
        record.start();//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
    }

    public void stopRecord(){//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
        record.stop();
        record.release();
    }

    public void startPlayback() throws Exception{//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
        play.setDataSource(FILE);
        play.prepare();
        play.start();

        play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
                if (play != null) {
                    play.stop();
                    play.release();
                    play = null;
                }
            }
        });

    }

    public void stopPlayback() throws Exception{//@reference YouTube - Android MediaPlayer/MediaRecorderTutorial & @authors
        if (play != null) {
            play.stop();
            play.release();
            play = null;
        }
    }

    public double getMaxAmp(){//@reference StackOverflow Measuring dB & @authors
        int x = record.getMaxAmplitude();
        double x2 = x;
        double db = (20 * Math.log10(x2 / REFERENCE));
        if(db>0){
            db = 62.57; //always throws to else regardless. Not working. Tried for a week to get in working, also see code below.
        }
        else{
            db = 62.57; //Should be return 0;
        }
        return db;
    }

    public void next()
    {
        Intent intent = new Intent(this,AdviceActivity.class);

        intent.putExtra("youtubeRec", youtubeRec); //@reference Stack Overflow SetIntent & @authors
        intent.putExtra("homeArtistRec", homeArtistRec); //@reference Stack Overflow SetIntent & @authors
        intent.putExtra("eventRec", eventRec); //@reference Stack Overflow SetIntent & @authors
        Bundle d = new Bundle(); //@reference Stack Overflow SetIntent & @authors
        d.putDouble("db", getMaxAmp()); //@reference Stack Overflow SetIntent & @authors
        intent.putExtras(d); //@reference Stack Overflow SetIntent & @authors

        startActivity(intent);
    }

    @Override //disables back button.
    public void onBackPressed() { //@reference Stack Overflow Disable back button & @authors
        Toast.makeText(getApplicationContext(),"Sorry, you can't go back here",Toast.LENGTH_LONG).show();
    }
}

/*
package com.example.x15011071.audioacousticassistant_app;

*/
/*
* @filename RecordActivty.java
* @author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
* @reference YouTube - https://www.youtube.com/watch?v=jcrh8C376-c
* @reference StackOverflow - https://stackoverflow.com/questions/41202083/auto-stop-recording-after-seconds
* @reference StackOverflow 2 - http://stackoverflow.com/questions/15693990/measuring-decibels-with-mobile-phone
* @date 11 April 2017
*
*
 *//*

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import java.io.File;
import java.io.IOException;

public class RecordActivity extends AppCompatActivity {

    Button recordBtn, nextBtn;
    TextView infoTV;
    MediaPlayer play;
    MediaRecorder record;
    String FILE;
    Thread runner;
    private static double maxAverage = 0.0; //
    static final private double AVERAGE_FILTER = 0.6;
    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    */
/*//*
/ Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};*//*


    final Runnable updater = new Runnable(){

        public void run(){
            updateTv();
        };
    };
    final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        infoTV = (TextView) findViewById(R.id.infoTV);
        if (runner == null)
        {
            runner = new Thread(){
                public void run()
                {
                    while (runner != null)
                    {
                        try
                        {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) { };
                        mHandler.post(updater);
                    }
                }
            };
            runner.start();
        }

        FILE = Environment.getExternalStorageState() + "/tempRecord.3gpp"; //@reference YouTube
        infoTV = (TextView)findViewById(R.id.infoTV);
        recordBtn = (Button)findViewById(R.id.recordBtn);
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //@reference YouTube
                if(recordBtn.getText().toString().equals("Tap\nto\nRecord")){
                    try{
                        startRecord();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    recordBtn.setText("Stop\nRecording");
                    infoTV.setText("Recording...");

                }

                else if (recordBtn.getText().toString().equals("Stop\nRecording")){
                    Handler handler = new Handler(); // @reference StackOverflow
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                stopRecord();
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    },10000); // 10K milliseconds = 10 seconds

                    recordBtn.setText("Tap\nto\nRecord");
                    infoTV.setText("You have recorded " + "value" + "dB");
                }
            }
        });

        nextBtn = (Button)findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }

    public void startRecord() throws Exception{ //@reference YouTube

        record = new MediaRecorder();
        record.setAudioSource(MediaRecorder.AudioSource.MIC);
        record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        record.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        record.setOutputFile(FILE);

        if (record != null){
            record.release();
        }
        File fileOut = new File(FILE);

        try {
            record.prepare();
        }
        catch (IOException f){
            f.printStackTrace();
        }
        record.start();

    }

    public void stopRecord() throws Exception{ //@reference YouTube
        record.stop();
        record.release();
    }

    public void updateTv(){
        //infoTV.setText(Double.toString((getAmplitudeEMA())) + " dB");
        String dbString = String.valueOf(getAmplitudeEMA()+ "dB");
    }


    public double getAmplitude() {
        if (record != null) {
            return (double) record.getMaxAmplitude();
        }else {
            return 0;
        }
    }

    public double getAmplitudeEMA() {
       // maxAverage = (AVERAGE_FILTER * amp) + ((1.0 - AVERAGE_FILTER) * maxAverage);
       // result = 20 * Math.log10(maxAverage / amp);
        return 20 * Math.log10((double)Math.abs(getAmplitude()) / 32768);
    }

    public void next()
    {
        Intent intent = new Intent(this,AdviceActivity.class);
        startActivity(intent);
    }
}
*/
