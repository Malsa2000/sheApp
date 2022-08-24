package com.example.sheapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.sheapp.Note.NoteModel;
import com.example.sheapp.SkinCare.AdapterSkinCare;
import com.example.sheapp.SkinCare.ModelReciprs;
import com.example.sheapp.SkinCare.SkinModel;
import com.example.sheapp.login.LoginActivity;
import com.example.sheapp.login.SigninActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirstPage extends AppCompatActivity {

    RecyclerView recipeContent;
    ArrayList<ModelReciprs> arrayList;
    AdapterRecipe adapter;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        String maskname = getIntent().getStringExtra("Maskcontent");


        //back and chang the path:
        databaseReference = db.getInstance().getReference("Care").child("SkinCare")
                .child(maskname).child("Maskcontent").child("nameofComponent");

        recipeContent =(RecyclerView)findViewById(R.id.recipe_content);
        recipeContent.setHasFixedSize(true);
        recipeContent.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        adapter = new AdapterRecipe(this, arrayList);
        recipeContent.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d :snapshot.getChildren()){
                   ModelReciprs note = d.getValue(ModelReciprs.class);
                    arrayList.add(note);

                    Toast.makeText(getApplicationContext(),d.getKey() ,Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("LogFragment", "loadLog:onCancelled", error.toException());
            }
        });

            }
        }

