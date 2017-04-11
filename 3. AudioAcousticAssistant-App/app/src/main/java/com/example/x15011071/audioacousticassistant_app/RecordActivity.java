package com.example.x15011071.audioacousticassistant_app;

/*
* @filename RecordActivty.java
* @author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
* @reference YouTube - https://www.youtube.com/watch?v=jcrh8C376-c
* @reference StackOverflow - https://stackoverflow.com/questions/41202083/auto-stop-recording-after-seconds
* @date 11 April 2017
*
*
 */

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

public class RecordActivity extends AppCompatActivity {

    Button recordBtn, nextBtn;
    TextView infoTV;
    MediaPlayer play;
    MediaRecorder record;
    String FILE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

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
        if (record != null){
            record.release();
        }

        File fileOut = new File(FILE);

        if(fileOut != null){
            fileOut.delete();
        }

        record = new MediaRecorder();
        record.setAudioSource(MediaRecorder.AudioSource.MIC);
        record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        record.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        record.setOutputFile(FILE);

        record.prepare();
        record.start();

    }

    public void stopRecord() throws Exception{ //@reference YouTube
        record.stop();
        record.release();
    }

    public void next()
    {
        Intent intent = new Intent(this,AdviceActivity.class);
        startActivity(intent);
    }
}
