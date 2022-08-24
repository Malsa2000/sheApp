package com.example.sheapp.width;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sheapp.R;
import com.example.sheapp.SettingActivity;

public class viewWidth extends AppCompatActivity {

    TextView width ;
    String text ;
    int number = 0;
    TextView thin , normal , fit , veryFit;
    ImageView shapeApple , ShapeClock , ShapePear , ShapeSequre ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_width);

        width = (TextView) findViewById(R.id.width_num);
        thin = (TextView) findViewById(R.id.thin_text);
        normal = (TextView) findViewById(R.id.normal_text);
        fit = (TextView) findViewById(R.id.fit_text);
        veryFit = (TextView) findViewById(R.id.very_fit_text);
        shapeApple = (ImageView) findViewById(R.id.shape_apple);
        ShapeClock  = (ImageView) findViewById(R.id.shape_clock);
        ShapePear  = (ImageView) findViewById(R.id.shape_pear);
        ShapeSequre = (ImageView) findViewById(R.id.shape_sequre);


        text= getIntent().getStringExtra("result" );
        width.setText(text);
        number = Integer.parseInt(text);



       if(number >= 1 && number <= 19 ){

           thin.setBackgroundColor(Color.argb(100, 137, 207, 240));
           //bgElement.setBackgroundColor(Color.WHITE);
       }
      else if(number >= 20 &&number <= 25 ){
           normal.setBackgroundColor(Color.argb(100, 137, 207, 240));

       }
       else if(number >= 26 &&number <=30  ){
           fit.setBackgroundColor(Color.argb(100, 137, 207, 240));

       }
       else if(number >= 31   ){
           veryFit.setBackgroundColor(Color.argb(100, 137, 207, 240));

       }






       ShapeClock.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(viewWidth.this , TypeBody.class);
               i.putExtra("result" , "shapeClock");
               startActivity(i);
           }
       });


        ShapeSequre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(viewWidth.this , TypeBody.class);
                i.putExtra("result" , "shapeSequre");
                startActivity(i);
            }
        });


        ShapePear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(viewWidth.this , TypeBody.class);
                i.putExtra("result" , "shapePear");

                startActivity(i);
            }
        });

        shapeApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(viewWidth.this , SettingActivity.class);
                i.putExtra("result" , "shapeApple");
                startActivity(i);
            }
        });
    }
}