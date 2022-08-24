package com.example.sheapp.HairCare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sheapp.R;

public class HairQustionCare extends AppCompatActivity  {

    Button check;
    Spinner q1 , q2 , q3 , q4;
    int numQustion1 = 0;
    int numQustion2 = 0;
    int numQustion3 = 0;
    int numQustion4 = 0;

    TextView t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_qustion_care);

        t =  findViewById(R.id.test);
        check = findViewById(R.id.check_button_hair);




        //qustion1
        q1 = (Spinner) findViewById(R.id.qustion1_per);
        ArrayAdapter<CharSequence> adapter1= ArrayAdapter.createFromResource(this,R.array.persentage,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        q1.setAdapter(adapter1);
        q1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String num = adapterView.getItemAtPosition(i).toString();

                if(num.equals("100%")){
                    numQustion1 = 100;
                }
                else if(num.equals("50%")){
                    numQustion1 = 50;
                }
                else if(num.equals("25%")){
                    numQustion1 = 25;
                }
                else if(num.equals("0%")){
                    numQustion1 = 0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //qustion2
        q2 = (Spinner) findViewById(R.id.qustion2_per);
        ArrayAdapter<CharSequence> adapter2= ArrayAdapter.createFromResource(this,R.array.persentage,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        q2.setAdapter(adapter2);
        q2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String num = adapterView.getItemAtPosition(i).toString();

                if(num.equals("100%")){
                    numQustion2 = 100;
                }
                else if(num.equals("50%")){
                    numQustion2 = 50;
                }
                else if(num.equals("25%")){
                    numQustion2 = 25;
                }
                else if(num.equals("0%")){
                    numQustion2 = 0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //qustion3
        q3 = (Spinner) findViewById(R.id.qustion3_per);
        ArrayAdapter<CharSequence> adapter3= ArrayAdapter.createFromResource(this,R.array.persentage,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        q3.setAdapter(adapter3);
        q3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String num = adapterView.getItemAtPosition(i).toString();

                if(num.equals("100%")){
                    numQustion3 = 100;
                }
                else if(num.equals("50%")){
                    numQustion3 = 50;
                }
                else if(num.equals("25%")){
                    numQustion3 = 25;
                }
                else if(num.equals("0%")){
                    numQustion3 = 0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //qustion1
        q4 = (Spinner) findViewById(R.id.qustion4_per);
        ArrayAdapter<CharSequence> adapter4= ArrayAdapter.createFromResource(this,R.array.persentage,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        q4.setAdapter(adapter4);
        q4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String num = adapterView.getItemAtPosition(i).toString();

                if(num.equals("100%")){
                    numQustion4 = 100;
                }
                else if(num.equals("50%")){
                    numQustion4 = 50;
                }
                else if(num.equals("25%")){
                    numQustion4 = 25;
                }
                else if(num.equals("0%")){
                    numQustion4 = 0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = numQustion1 +numQustion2 + numQustion3 + numQustion4;
                String n = String.valueOf( result );
                t.setText(n);

                if(result ==300){
                    Intent i = new Intent(HairQustionCare.this , ResultHairCare.class);
                    i.putExtra("type" , "oily");
                    startActivity(i);
                }
                else if(result ==225 || result ==200){
                    Intent i = new Intent(HairQustionCare.this , ResultHairCare.class);
                    i.putExtra("type" , "normal");
                    startActivity(i);
                }
                else if(result ==100){
                    Intent i = new Intent(HairQustionCare.this , ResultHairCare.class);
                    i.putExtra("type" , "dry");
                    startActivity(i);
                }
                else {
                        Intent i = new Intent(HairQustionCare.this , ResultHairCare.class);
                        i.putExtra("type" , "combination");
                        startActivity(i);
                    }
           }
        });
    }
}