package com.example.sheapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class FragmantAddComment extends Fragment {

 EditText comment;
 RecyclerView recycel_comment;
 String commentText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.comment_fragment, container, false);
        comment = v.findViewById(R.id.add_new_comment);
        commentText = comment.getText().toString();


        recycel_comment = v.findViewById(R.id.recycel_comment);


        return v;
    }
    }

