package com.example.sheapp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheapp.Admin.AdminAddNewContent;
import com.example.sheapp.MainActivity;
import com.example.sheapp.R;
import com.example.sheapp.SettingActivity;
import com.example.sheapp.width.goodWidth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    TextView  forginPass;
    Button loginButton , signin ;
    EditText Email , Password;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auther;
    ImageView admainPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        forginPass = (TextView) findViewById( R.id.fordit_pss);
        loginButton = (Button) findViewById( R.id.login_button);
        Email = (EditText) findViewById( R.id.edit_name_login);
        Password = (EditText) findViewById( R.id.edit_pass_login);
        admainPhoto = (ImageView) findViewById( R.id.adiman_page);
        signin = (Button) findViewById( R.id.signin_button);



        loginButton.setOnClickListener(this);
        forginPass.setOnClickListener(this);
        signin.setOnClickListener(this);
        admainPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.adiman_page:
                Intent intent5 = new Intent(LoginActivity.this , SettingActivity.class);
                startActivity(intent5);

                break;
            case  R.id.login_button:
               login();
               Intent intent = new Intent(LoginActivity.this , MainActivity.class);
               startActivity(intent);

                break;
            case  R.id.fordit_pss:
                loginMethods();
                Intent intent1 = new Intent(LoginActivity.this , AdminAddNewContent.class);
                startActivity(intent1);
                break;

            case  R.id.singin_button:
                loginMethods();
                Intent intent2 = new Intent(LoginActivity.this , SigninActivity.class);
                startActivity(intent2);
                break;


        }
    }

    //to login
    private void loginMethods() {

        String email = Email.getText().toString().trim();
        String pass = Password.getText().toString().trim();

        //check the user authuntcation :
        if(email.isEmpty()){
            Email.setError("full the fild");
            Email.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            Email.setError("full the password");
            Email.requestFocus();
            return;
        }

    }


    public void login(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {

            String value = Email.getText().toString();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child(value).exists()) {
                    Toast.makeText(getApplicationContext(), "Sucsess login  ", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "not found user or you do not have Account! ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}