package com.example.sheapp.HairCare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sheapp.R;

public class ResultHairCare extends AppCompatActivity {


    ImageView resultImage ;
    TextView resultText;
    String text;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_hair_care);
        resultImage = (ImageView) findViewById(R.id.image_result_type);
        resultText = (TextView) findViewById(R.id.text_result_type);

        text = getIntent().getStringExtra("type");
      //  ok = findViewById(R.id.hair_ok_button);


        if(text.equals("oily")){
            resultText.setText("Great, you have oily skin");
            resultImage.setImageResource(R.drawable.hair1);
        }
        else  if(text.equals("normal")){
            resultText.setText("Great, you have normal skin");
            resultImage.setImageResource(R.drawable.hire2);

        }
        else  if(text.equals("dry")){
            resultText.setText("Great, you have dry skin ");
            resultImage.setImageResource(R.drawable.hire3);
        }
        else  if(text.equals("combination")) {
            resultText.setText("Great, you have combination skin");
            resultImage.setImageResource(R.drawable.sensitive);
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultHairCare.this , HairCare.class);
                startActivity(i);
            }
        });

    }
}