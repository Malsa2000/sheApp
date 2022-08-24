package com.example.sheapp.width;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheapp.R;

public class goodWidth extends AppCompatActivity {


    TextView calclate ;
    SeekBar lenght , width ;
    int lenghtNmu;
    int widthNum ;
    int result;

    TextView lenghtText , widthText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_width);

        calclate = (TextView) findViewById(R.id.calclate_width);
        lenght = (SeekBar) findViewById(R.id.lenght);
        width = (SeekBar) findViewById(R.id.weight);
        lenghtText = (TextView) findViewById(R.id.text_lenght);
        widthText = (TextView) findViewById(R.id.text_width);

        //methods seekBar

lenght.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        lenghtNmu  = i;
        String x = String.valueOf(lenghtNmu);
        lenghtText.setText(x);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
      //  lenghtText.setText(lenghtNmu);

    }
});

        width.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                widthNum  = i;
                String x = String.valueOf(widthNum);
                widthText.setText(x);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //  lenghtText.setText(lenghtNmu);

            }
        });

// on button click
calclate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        //claclate good width
        result = (widthNum * widthNum) / lenghtNmu ;

        String v = String.valueOf(result);
        //send data into next page
        if(result != 0){
        Intent i = new Intent(goodWidth.this , viewWidth.class);
        i.putExtra("result" , v);
        startActivity(i);
       }

        //if user input 0 value
        else {
            Toast.makeText(getApplicationContext() ,R.string.error1,Toast.LENGTH_LONG).show();
        }
//

    }
});
    }
}