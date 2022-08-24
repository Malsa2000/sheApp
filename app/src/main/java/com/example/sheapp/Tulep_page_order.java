package com.example.sheapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheapp.Note.MyAdapterNote;
import com.example.sheapp.Note.NoteModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tulep_page_order extends AppCompatActivity {

    RecyclerView recycelOrder;
    ArrayList<ModelOrder> orderList;
    AdapterOreder adapterOreder;
    DatabaseReference databaseReference;
    TextView allOrders ;
    NetworkFaild networkFaild = new NetworkFaild();
    int count =0;
    String id ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulep_page_order);
        recycelOrder = findViewById(R.id.order);
        allOrders = findViewById(R.id.all_orders);

        id = getIntent().getStringExtra("product_id");

        databaseReference = FirebaseDatabase.getInstance().getReference("order").child("id");
        recycelOrder.setHasFixedSize(true);
        recycelOrder.setLayoutManager(new LinearLayoutManager(this));
        orderList = new ArrayList<>();
        adapterOreder = new AdapterOreder(this, orderList);
        recycelOrder.setAdapter(adapterOreder);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d : snapshot.getChildren()) {
                    ModelOrder orderListFirbase = d.getValue(ModelOrder.class);
                    orderList.add(orderListFirbase);
                }

                adapterOreder.notifyDataSetChanged();
                count = orderList.size();
                allOrders.setText(count);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext() ," Failed to lode data " ,Toast.LENGTH_LONG).show();
            }
        });

    }



}