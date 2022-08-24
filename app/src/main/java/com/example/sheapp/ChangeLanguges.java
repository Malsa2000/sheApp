package com.example.sheapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class ChangeLanguges extends AppCompatActivity {

Button change ;
RadioButton arabic , english;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_languges);

        change = (Button)  findViewById(R.id.change_languges);
        arabic = (RadioButton) findViewById(R.id.arrabic_languges);
        english = (RadioButton) findViewById(R.id.english_languges);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (arabic.getText().equals("Arabic") ){

                }
            }
        });

    }
}