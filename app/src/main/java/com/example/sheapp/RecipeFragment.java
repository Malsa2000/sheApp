package com.example.sheapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sheapp.Note.MyAdapterNote;
import com.example.sheapp.Note.NoteModel;
import com.example.sheapp.SkinCare.ModelReciprs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeFragment extends Fragment {

    RecyclerView recipeContent;
    ArrayList<ModelReciprs> arrayList;
    AdapterRecipe adapter;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.recipe_fragment, container, false);

        String maskname = getActivity().getIntent().getStringExtra("Maskcontent");


        //back and chang the path:
        databaseReference = db.getInstance().getReference("Care").child("SkinCare")
                .child(maskname).child("Maskcontent");


        recipeContent =(RecyclerView) v.findViewById(R.id.recipe_content);
        recipeContent.setHasFixedSize(true);
        recipeContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList = new ArrayList<>();
        adapter = new AdapterRecipe(getActivity(),arrayList);
        recipeContent.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d :snapshot.getChildren()){
                    ModelReciprs mode = d.getValue(ModelReciprs.class);
                    arrayList.add(mode);
                    Toast.makeText(getActivity(),d.getKey() ,Toast.LENGTH_SHORT).show();

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("LogFragment", "loadLog:onCancelled", error.toException());
            }
        });
        return v;
    }
    }

