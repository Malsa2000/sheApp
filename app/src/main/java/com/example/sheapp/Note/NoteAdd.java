package com.example.sheapp.Note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sheapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;

public class NoteAdd extends AppCompatActivity {

    CalendarView calendar;
    EditText EventName, EventDescrpion;
    String Date;
    Button add , view ;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Note");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);

        calendar = findViewById(R.id.calender);
        EventName = findViewById(R.id.note_name);
        EventDescrpion = findViewById(R.id.note_descrapition);
        add = findViewById(R.id.add_note);
        view = findViewById(R.id.view);


        // to get date from user :
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                Date = i2 + "-" + (i1 + 1) + "-" + i;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LodeContentInFireBase();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NoteView.class);
                startActivity(i);
            }
        });

    }

    private void LodeContentInFireBase() {
        String nameEvent = EventName.getText().toString().trim();
        String descriptionEvent = EventDescrpion.getText().toString().trim();

        //check the user authuntcation :
        if(nameEvent.isEmpty()){
            EventName.setError("full the fild");
            EventName.requestFocus();
            return;
        }

        if(nameEvent.isEmpty()){
            EventName.setError("full the fild");
            EventName.requestFocus();
            return;
        }

//        root.setValue(Date);
//        HashMap<String, Object> usermape = new HashMap<>();
//        usermape.put("EventName", nameEvent);
//        usermape.put("EventDescription", descriptionEvent);
//        usermape.put("date", Date);

        root.push().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DatabaseReference f = root.push();
                f.child("EventName").setValue(nameEvent);
                f.child("EventDescription").setValue(descriptionEvent);
                f.child("date").setValue(Date);
//
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}