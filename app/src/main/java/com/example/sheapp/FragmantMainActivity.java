package com.example.sheapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sheapp.HairCare.HairQustionCare;
import com.example.sheapp.Note.NoteView;
import com.example.sheapp.SkinCare.Arraylist;
import com.example.sheapp.SkinCare.MyAdapter;
import com.example.sheapp.SkinCare.SkinType;
import com.example.sheapp.width.goodWidth;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmantMainActivity extends Fragment {

    RecyclerView mainTypeOfCare;
    MyAdapter Adapter;
    private ArrayList<Arraylist> type;
    LinearLayout skinCare , hairCare , noteCare , bodyCare , homeAtomation;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_main_activity, container, false);

        mainTypeOfCare = v.findViewById(R.id.recycell_main_care);
        skinCare = v.findViewById(R.id.skin_care_main);
        hairCare = v.findViewById(R.id.hire_care_main);
        noteCare = v.findViewById(R.id.note_care);
        bodyCare = v.findViewById(R.id.width_care_main);
        homeAtomation = v.findViewById(R.id.home_atomation_main);


        type = new ArrayList<>();

        type = new ArrayList<Arraylist>();
        TypeMainModel t = new TypeMainModel();
        for (int i = 0; i < t.announcement.length; i++) {
            type.add(new Arraylist(
                    t.announcement[i]
            ));

            Adapter=new MyAdapter(type);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            mainTypeOfCare.setLayoutManager(horizontalLayoutManagaer);
            mainTypeOfCare.setAdapter(Adapter);
        }

        skinCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity() , SkinType.class);
                startActivity(i);
            }
        });
        hairCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity() , HairQustionCare.class);
                startActivity(i);
            }
        });

        noteCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity() , NoteView.class);
                startActivity(i);
            }
        });

        bodyCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity() , goodWidth.class);
                startActivity(i);
            }
        });
        homeAtomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity() , HomeAto.class);
                startActivity(i);
            }
        });
        return v;
    }
    }

