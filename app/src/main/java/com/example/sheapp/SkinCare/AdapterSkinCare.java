package com.example.sheapp.SkinCare;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheapp.FirstPage;
import com.example.sheapp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterSkinCare extends RecyclerView.Adapter<AdapterSkinCare.ViewHolderSkin> implements Filterable {

    public ArrayList<SkinModel> allMask;
    public ArrayList<SkinModel> allMaskFull;

    Context context;

    public AdapterSkinCare(ArrayList<SkinModel> horizontalList , Context c) {

        allMask = horizontalList;
        allMaskFull = new ArrayList<>();
        context = c;
    }



    @NonNull
    @Override
    public AdapterSkinCare.ViewHolderSkin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row_skin, parent, false);


        AdapterSkinCare.ViewHolderSkin myViewHolder = new AdapterSkinCare.ViewHolderSkin(view);
        return myViewHolder;
      //  return new ViewHolderSkin(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSkinCare.ViewHolderSkin holder, int position) {

        SkinModel model = allMask.get(position);


        holder.nameMask.setText(model.getMaskName());
        holder.Descripion.setText(model.getMaskDescription());
          holder.imagemask.setImageURI(Uri.parse(model.getMaskimage()));


//               Glide.with(context)
//                .load(model.getMaskimage())
//                .into(holder.imagemask);
//           Toast.makeText(context , (CharSequence) Uri.parse(model.getMaskimage()),Toast.LENGTH_SHORT).show();

//        Picasso.with(context)//or context
//                .load(model.getMaskimage())
//                .fit()
//                .into(holder.imagemask);





        String x = model.getMaskName();
         holder.layout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Toast.makeText(context,"malsa" ,Toast.LENGTH_SHORT).show();
                 Intent i = new Intent(view.getContext(), FirstPage.class);
                 i.putExtra("Maskcontent" , x);
                 view.getContext().startActivity(i);


             }
         });


    }

    @Override
    public int getItemCount() {
      return allMask.size();

    }

    @Override
    public Filter getFilter() {
        return maskFilter;
    }

    private Filter maskFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

             ArrayList<SkinModel> filterList = new ArrayList<>();
             if(charSequence ==null || charSequence.length() ==0){
                 filterList.addAll(allMaskFull);
             }
             else {
                 String s = charSequence.toString().toLowerCase().trim();

                 for (SkinModel item : allMaskFull){
                     if(item.getMaskName().toLowerCase().contains(s)){
                         filterList.add(item);
                     }
                 }
             }

            FilterResults results = new FilterResults();
            results.values = filterList;

            return results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            allMask.clear();
            allMask.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderSkin extends RecyclerView.ViewHolder {

        TextView nameMask , Descripion;
        ImageView imagemask;
        LinearLayout layout;


        public ViewHolderSkin(@NonNull View itemView) {
            super(itemView);
            nameMask = (TextView) itemView.findViewById(R.id.name_mask);
            Descripion = (TextView) itemView.findViewById(R.id.descraption_mask);
            imagemask = (ImageView) itemView.findViewById(R.id.image_mask);
            layout = (LinearLayout) itemView.findViewById(R.id.item);


        }}
}
