package com.example.sheapp.width;

import androidx.appcompat.app.AppCompatActivity;
import pl.droidsonroids.gif.GifImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheapp.R;

public class TypeBody extends AppCompatActivity {


    ImageView bodyType;
    TextView  titel,textType , description;
    TextView link;
    GifImageView  image1 , image2 , image3;

  String shaepName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_body);

        bodyType = findViewById(R.id.image_type_body);
        titel = findViewById(R.id.title);
        textType = findViewById(R.id.name_shape_body);
        description = findViewById(R.id.body_description);
        link = findViewById(R.id.link_text);

        image1 = findViewById(R.id.gif_exersise_1);
        image2 = findViewById(R.id.gif_exersise_2);
        image3 = findViewById(R.id.gif_exersise_3);

        shaepName= getIntent().getStringExtra("result" );

        if(shaepName.equals("shapeClock")){
            titel.setText(R.string.houre_shape);
            textType.setText(R.string.houre_shape);
            description.setText(R.string.hour_desc);
            bodyType.setImageResource(R.drawable.clock_shap);
            image1.setImageResource(R.drawable.exercise_white_1);
            image2.setImageResource(R.drawable.exersise_jump);
            image3.setImageResource(R.drawable.exersise_white_3);


        }
       else if(shaepName.equals("shapeSequre")){
            titel.setText(R.string.rectangel_shape);
            textType.setText(R.string.rectangel_shape);
            description.setText(R.string.rectangel_desc);
            bodyType.setImageResource(R.drawable.recte_shap);
            image1.setImageResource(R.drawable.exersises_1);
            image2.setImageResource(R.drawable.exrtsise_7);
            image3.setImageResource(R.drawable.exersise_white_4);

        }
        else if(shaepName.equals("shapePear")){
            titel.setText(R.string.pear_shape);
            textType.setText(R.string.pear_shape);
            description.setText(R.string.pear_desc);
            bodyType.setImageResource(R.drawable.pean_shap);
            image1.setImageResource(R.drawable.exersise_4);
            image2.setImageResource(R.drawable.exersise_5);
            image3.setImageResource(R.drawable.exersise_8);

        }
        else if(shaepName.equals("shapeApple")){
            titel.setText(R.string.apple_shape);
            textType.setText(R.string.apple_shape);
            description.setText(R.string.apple_decription);
            bodyType.setImageResource(R.drawable.apple_shape);
            image1.setImageResource(R.drawable.exercise_2);
            image2.setImageResource(R.drawable.exercise_6);
            image3.setImageResource(R.drawable.exersise_bend);


        }

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shaepName.equals("shapeClock")){
                   Toast.makeText(getApplicationContext() , "shapeClock",Toast.LENGTH_SHORT).show();

                }
                else if(shaepName.equals("shapeSequre")){
                    Toast.makeText(getApplicationContext() , "shapeSequre",Toast.LENGTH_SHORT).show();


                }
                else if(shaepName.equals("shapePear")){
                    Toast.makeText(getApplicationContext() , "shapePear",Toast.LENGTH_SHORT).show();


                }
                else if(shaepName.equals("shapeApple")){
                    Toast.makeText(getApplicationContext() , "shapeApple",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}