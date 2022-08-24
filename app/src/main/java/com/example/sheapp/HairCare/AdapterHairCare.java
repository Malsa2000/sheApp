package com.example.sheapp.HairCare;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sheapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterHairCare extends RecyclerView.Adapter<AdapterHairCare.MyViewHolder> {


    Context context ;
    ArrayList<ModelHair> arrayList;

    public AdapterHairCare(ArrayList<ModelHair> arrayList, Context applicationContext) {
        this.arrayList = arrayList;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.my_row_skin,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ModelHair modelHair = arrayList.get(position);
        holder.nameMask.setText(modelHair.getMaskName());
        holder.descriptionMask.setText(modelHair.getMaskDescription());
        //holder.maskImage.setImageURI(Uri.parse(modelHair.getMaskimage()));
        Glide.with(context)
                .load(modelHair.getMaskimage()).into(holder.maskImage);

        Picasso.with(context)//or context
                .load(modelHair.getMaskimage())
                .fit()
                .into(holder.maskImage);





    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView maskImage;
        TextView nameMask , descriptionMask;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            maskImage = (ImageView) itemView.findViewById(R.id.image_mask);
            nameMask = (TextView) itemView.findViewById(R.id.name_mask);
            descriptionMask = (TextView) itemView.findViewById(R.id.descraption_mask);


        }
    }
}
