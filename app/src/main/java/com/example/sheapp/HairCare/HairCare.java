package com.example.sheapp.HairCare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sheapp.R;
import com.example.sheapp.SkinCare.AdapterSkinCare;
import com.example.sheapp.SkinCare.SkinModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HairCare extends AppCompatActivity {

    RecyclerView haireRecycellView;
    AdapterHairCare adapter;
    ArrayList<ModelHair> arrayList;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Care").child("HairCare");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_care);

        arrayList = new ArrayList<>();
        adapter=new AdapterHairCare(arrayList , getApplicationContext());

        haireRecycellView = findViewById(R.id.all_mask);
        haireRecycellView.setHasFixedSize(true);

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        haireRecycellView.setLayoutManager(horizontalLayoutManagaer);
        haireRecycellView.setAdapter(adapter);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelHair model = dataSnapshot.getValue(ModelHair.class);
                    arrayList.add(model);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}