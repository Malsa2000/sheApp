package com.example.sheapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sheapp.SkinCare.SkinCare;
import com.example.sheapp.width.TypeBody;

public class TypeResult extends AppCompatActivity {

    ImageView resultImage ;
    TextView resultText;
    String text;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_result);

        resultImage = (ImageView) findViewById(R.id.image_result_type);
        resultText = (TextView) findViewById(R.id.text_result_type);
        ok = (Button) findViewById(R.id.skin_button);


        text = getIntent().getStringExtra("type");


        if(text.equals("oily")){
            resultText.setText("Great, you have oily skin");
            resultImage.setImageResource(R.drawable.oil);
        }
      else  if(text.equals("normal")){
            resultText.setText("Great, you have normal skin");
            resultImage.setImageResource(R.drawable.normal);

        }
        else  if(text.equals("dry")){
            resultText.setText("Great, you have dry skin ");
            resultImage.setImageResource(R.drawable.dry);
        }
        else  if(text.equals("combination")) {
            resultText.setText("Great, you have combination skin");
            resultImage.setImageResource(R.drawable.sensitive);
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dailoge();

            }
        });

    }

    public void dailoge(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        View layout_dialog = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dailog_result ,null);

        AppCompatButton yesButton = layout_dialog.findViewById(R.id.yes_button);
        AppCompatButton noButton = layout_dialog.findViewById(R.id.no_button);

        builder.setView(layout_dialog);

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setGravity(Gravity.CENTER);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent i = new Intent(TypeResult.this , SkinCare.class);
                i.putExtra("type" ,text);
                startActivity(i);
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent i = new Intent(TypeResult.this , SkinCare.class);
                startActivity(i);
            }
        });
    }
}