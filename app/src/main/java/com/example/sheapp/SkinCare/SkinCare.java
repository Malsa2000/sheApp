package com.example.sheapp.SkinCare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.SearchView;
import android.widget.Toolbar;

import com.example.sheapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SkinCare extends AppCompatActivity {

    SearchView searsh;
    RecyclerView allMask;
    AdapterSkinCare Adapter;
    public ArrayList<SkinModel> Masks;
    Toolbar toolbar ;
    String text;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Care").child("SkinCare");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_care);

//        text = getIntent().getStringExtra("type");
//        if(text.equals("oily")){
//
//        }
//        else  if(text.equals("normal")){
//
//
//        }
//        else  if(text.equals("dry")){
//
//        }
//        else  if(text.equals("combination")) {
//
//        }

        searsh = (SearchView) findViewById(R.id.searshview);
        Masks = new ArrayList<>();
        Adapter=new AdapterSkinCare(Masks , getApplicationContext());




        allMask = findViewById(R.id.all_mask);
        allMask.setHasFixedSize(true);

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        allMask.setLayoutManager(horizontalLayoutManagaer);
        allMask.setAdapter(Adapter);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                 SkinModel model = dataSnapshot.getValue(SkinModel.class);
                 Masks.add(model);
                }

                Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        searsh.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Adapter.getFilter().filter(s);
                return true;
            }

        });





    }




}