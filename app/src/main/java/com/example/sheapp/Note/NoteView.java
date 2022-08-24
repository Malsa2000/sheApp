package com.example.sheapp.Note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheapp.NetworkFaild;
import com.example.sheapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoteView extends AppCompatActivity {


    LinearLayout addNewNote;
    RecyclerView recyclerViewNote;
    MyAdapterNote adapter;
    ArrayList<NoteModel> noteList;
    DatabaseReference databaseReference;
    TextView t ;
     NetworkFaild networkFaild = new NetworkFaild();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);


        addNewNote = (LinearLayout) findViewById(R.id.add_new_note);
        recyclerViewNote = findViewById(R.id.user_event);

        if(!isConnected(this)){
            IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(networkFaild ,filter);
        }

        addNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NoteView.this, NoteAdd.class);
                startActivity(i);
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("Note");
        recyclerViewNote.setHasFixedSize(true);
        recyclerViewNote.setLayoutManager(new GridLayoutManager(this , 2));
        noteList = new ArrayList<>();
        adapter = new MyAdapterNote(this, noteList);
        recyclerViewNote.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d : snapshot.getChildren()) {
                    NoteModel note = d.getValue(NoteModel.class);
                    noteList.add(note);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext() ," Failed to lode data " ,Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onStop() {
        //unregisterReceiver(networkFaild);
        super.onStop();
    }

    private boolean isConnected(NoteView noteView) {

        ConnectivityManager manager = (ConnectivityManager) noteView.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobil = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(networkInfo != null && networkInfo.isConnected() || mobil != null && mobil.isConnected() ){
            return true;
        }
        else {
            return false;
        }
    }
}



//    @Override protected void onStart()
//    {
//        super.onStart();
//        adapter.st
//    }
//
//    @Override protected void onStop()
//    {
//        super.onStop();
//        adapter.stopListening();
//    }

