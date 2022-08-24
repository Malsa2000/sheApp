package com.example.sheapp.SkinCare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheapp.R;
import com.example.sheapp.TypeResult;

public class SkinType extends AppCompatActivity {


    RadioGroup qustion1 , qustion2 ,qustion3 , qustion4;
    RadioButton qustion11 , qustion12 , qustion13;
    RadioButton qustion21 , qustion22 , qustion23;
    RadioButton qustion31 , qustion32 , qustion33;
    RadioButton qustion41 , qustion42 ;
    TextView check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_type);

        //RadioGroup (qustion)
        qustion1 = (RadioGroup) findViewById(R.id.question1);
        qustion2 = (RadioGroup) findViewById(R.id.question2);
        qustion3 = (RadioGroup) findViewById(R.id.question3);
        qustion4 = (RadioGroup) findViewById(R.id.question4);

        //RadioButton (possible )
        qustion11 = (RadioButton) findViewById(R.id.posible1);
        qustion12 = (RadioButton) findViewById(R.id.posible2);
        qustion13 = (RadioButton) findViewById(R.id.posible3);
        qustion21 = (RadioButton) findViewById(R.id.posible21);
        qustion22 = (RadioButton) findViewById(R.id.posible22);
        qustion23 = (RadioButton) findViewById(R.id.posible23);
        qustion31 = (RadioButton) findViewById(R.id.posible31);
        qustion32 = (RadioButton) findViewById(R.id.posible32);
        qustion33 = (RadioButton) findViewById(R.id.posible33);
        qustion41 = (RadioButton) findViewById(R.id.posible41);
        qustion42 = (RadioButton) findViewById(R.id.posible42);

        //check Button:
        check = (TextView) findViewById(R.id.check_button);

   check.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        if(qustion11.getText().equals("100%") || qustion21.getText().equals("100%")
           || qustion31.getText().equals("0%")   || qustion31.getText().equals("100%")){

            Toast.makeText(getApplicationContext() ,"oily",Toast.LENGTH_LONG).show();

            Intent i = new Intent(SkinType.this , TypeResult.class);
            i.putExtra("type" , "oily");
            startActivity(i);

        }
        else if(qustion11.getText().equals("50%") && qustion21.getText().equals("50%")
                && qustion31.getText().equals("50%")   && qustion31.getText().equals("50%")){

            Intent i = new Intent(SkinType.this , TypeResult.class);
            i.putExtra("type" , "normal");
            startActivity(i);

        }
        else  if(qustion11.getText().equals("0%") && qustion21.getText().equals("0%")
                && qustion31.getText().equals("100%")   && qustion31.getText().equals("0%")){

            Intent i = new Intent(SkinType.this , TypeResult.class);
            i.putExtra("type" , "dry");
            startActivity(i);

        }
        else {

            Intent i = new Intent(SkinType.this , TypeResult.class);
            i.putExtra("type" , "combination");
            startActivity(i);

        }


    }
});

    }
}