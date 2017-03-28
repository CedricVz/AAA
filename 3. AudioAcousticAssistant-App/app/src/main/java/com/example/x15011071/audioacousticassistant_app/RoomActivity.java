package com.example.x15011071.audioacousticassistant_app;


        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;


public class RoomActivity extends AppCompatActivity {
    TextView input1;
    TextView input2;
    TextView input3;

    TextView tv_result;
    TextView tv_result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);


        input1 = (EditText) findViewById(R.id.et_input1);
        input2 = (EditText) findViewById(R.id.et_input2);
        input3 = (EditText) findViewById(R.id.et_input3);

        Button bt_calculate;
        bt_calculate = (Button) findViewById(R.id.bt_calculate);

        tv_result = (TextView) findViewById(R.id.tv_result);
        tv_result2 = (TextView) findViewById(R.id.tv_result2);

        bt_calculate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                makeCalculations();
            }
        });
    }
    public void fets(View view){
        TextView textView = (TextView) findViewById(R.id.tv_result2);
        textView.setVisibility(View.VISIBLE);
        TextView textView2 = (TextView) findViewById(R.id.tv_result);
        textView2.setVisibility(View.INVISIBLE);
        }
    public void mets(View view){
        TextView textView = (TextView) findViewById(R.id.tv_result);
        textView.setVisibility(View.VISIBLE);
        TextView textView2 = (TextView) findViewById(R.id.tv_result2);
        textView2.setVisibility(View.INVISIBLE);
    }

    public void calculateRoom(View v){}
    private void makeCalculations() {

        double n1 = Double.valueOf(input1.getText().toString());
        double n2 = Double.valueOf(input2.getText().toString());
        double n3 = Double.valueOf(input3.getText().toString());


        double result= n1*n2*n3;
//        double conversion = result*35.31;
        double conversion = result*3.281;
        tv_result.setText("The result is: " + result);
        tv_result2.setText("The result is: " + conversion);



    }
    public void Next(View view)
    {
        String button_text;
        button_text=((Button)view).getText().toString();
        if(button_text.equals("Next"))
        {
            Intent intent = new Intent(this,RecordActivity.class);
            startActivity(intent);
        }
        else
        {

        }
    }
}
