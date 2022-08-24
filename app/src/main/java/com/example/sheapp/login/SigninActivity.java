package com.example.sheapp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheapp.MainActivity;
import com.example.sheapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener{


    Button singButton;
    EditText Email , Password , age , name ;
    FirebaseAuth auther;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference  root = db.getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        singButton = (Button) findViewById( R.id.singin_button);
        Email = (EditText) findViewById( R.id.edit_email_sign);
        Password = (EditText) findViewById( R.id.edit_pass_sign);
        age = (EditText) findViewById( R.id.edit_age_sign);
        name = (EditText) findViewById( R.id.edit_name_sign);

        auther = FirebaseAuth.getInstance();

        singButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case  R.id.singin_button:
               singMethods();
                //signinUser();
                Toast.makeText(this, "scussess", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void signinUser() {

        String email = Email.getText().toString().trim();
        String pass = Password.getText().toString().trim();
        String name = Password.getText().toString().trim();
        String age = Password.getText().toString().trim();

        root.child("Users").setValue(name);
        HashMap<String, String> usermape =new HashMap<>();
        usermape.put("name" , name);
        usermape.put("email" , email);
        usermape.put("age" , age);
        usermape.put("password" , pass);

        root.push().setValue(usermape);



    }

    private void singMethods() {

        String email = Email.getText().toString().trim();
        String pass = Password.getText().toString().trim();
        String name = Password.getText().toString().trim();
        String age = Password.getText().toString().trim();

        //check the user authuntcation :
        if(email.isEmpty()){
            Email.setError("full the fild");
            Email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("please write the correct email");
            Email.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            Email.setError("full the password");
            Email.requestFocus();
            return;
        }
        if(age.isEmpty()){
            Email.setError("full the age");
            Email.requestFocus();
            return;
        }
        if(name.isEmpty()){
            Email.setError("full the Name");
            Email.requestFocus();
            return;
        }
        auther.createUserWithEmailAndPassword(email ,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(name , email , age ,pass);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext() , "add user sucsessful ",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


                   Intent i = new Intent(SigninActivity.this , MainActivity.class);
                   startActivity(i);
    }
}