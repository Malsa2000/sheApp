package com.example.sheapp.SkinCare;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheapp.HairCare.HairQustionCare;
import com.example.sheapp.Note.NoteView;
import com.example.sheapp.R;
import com.example.sheapp.width.goodWidth;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Arraylist> dataSet;

    public MyAdapter(ArrayList<Arraylist> horizontalList) {

        dataSet = horizontalList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row_main_activity, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ImageView imageView = holder.imageViewIcon;
//        imageView.setImageResource(dataSet.get(position).getImge());
//
//        TextView text1 = holder.name;
//        text1.setText(dataSet.get(position).getName());
//
//        TextView text2 = holder.description;
//        text2.setText(dataSet.get(position).getDesc());

        LinearLayout main = holder.main;
        int number = position;
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(number ==0){
                  Toast.makeText(view.getContext(), R.string.skin_care, Toast.LENGTH_SHORT).show();
                  Intent i = new Intent(view.getContext(), SkinType.class);
                  view.getContext().startActivity(i);
              }
              else if(number ==1){
                      Toast.makeText(view.getContext(), " goodWidth", Toast.LENGTH_SHORT).show();
                      Intent i = new Intent(view.getContext(), goodWidth.class);
                      view.getContext().startActivity(i);

                  }
              else if(number ==2){
                      Toast.makeText(view.getContext(),  R.string.hair_care, Toast.LENGTH_SHORT).show();
                      Intent i = new Intent(view.getContext(), HairQustionCare.class);
                      view.getContext().startActivity(i);
                  }
              else if(number ==3){
                      Toast.makeText(view.getContext(),  R.string.note, Toast.LENGTH_SHORT).show();
                      Intent i = new Intent(view.getContext(), NoteView.class);
                      view.getContext().startActivity(i);
                  }

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewIcon;
        TextView name ,description;
        LinearLayout main;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.main = (LinearLayout) itemView.findViewById(R.id.main);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.image);
            this.name = (TextView) itemView.findViewById(R.id.title_name);
            this.description = (TextView) itemView.findViewById(R.id.description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getPosition() == 0) {
                        Toast.makeText(v.getContext(), R.string.skin_care, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(v.getContext(), SkinType.class);
                        v.getContext().startActivity(i);

                    }
                    if (getPosition() == 1) {
                        Toast.makeText(v.getContext(), " goodWidth", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(v.getContext(), goodWidth.class);
                        v.getContext().startActivity(i);

                    }
                    if (getPosition() == 2) {
                        Toast.makeText(v.getContext(),  R.string.hair_care, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(v.getContext(), HairQustionCare.class);
                        v.getContext().startActivity(i);
                    }
                    if (getPosition() == 3) {
                        Toast.makeText(v.getContext(),  R.string.note, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(v.getContext(), NoteView.class);
                        v.getContext().startActivity(i);
                    }
                }

            });
        }}
}
