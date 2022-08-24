package com.example.sheapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sheapp.SkinCare.AdapterSkinCare;
import com.example.sheapp.SkinCare.ModelReciprs;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterRecipe extends RecyclerView.Adapter<AdapterRecipe.MyViewHolder> {
Context context;
ArrayList<ModelReciprs> arrayList ;

    public AdapterRecipe(Context context, ArrayList<ModelReciprs> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row_recipe, parent, false);
        AdapterRecipe.MyViewHolder myViewHolder = new AdapterRecipe.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         ModelReciprs model = arrayList.get(position);
        holder.name_content.setText(model.getNameofComponent());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView name_content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_content = itemView.findViewById(R.id.name_content);
        }
    }
}
