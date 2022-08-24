package com.example.sheapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sheapp.Note.MyAdapterNote;
import com.example.sheapp.Note.NoteModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterOreder extends RecyclerView.Adapter<AdapterOreder.ViewHolderOreder> {
    Context cont;
    ArrayList<ModelOrder> orderList;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("order");

    public AdapterOreder(Tulep_page_order tulep_page_order, ArrayList<ModelOrder> orderList) {
        this.cont = tulep_page_order;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolderOreder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(cont).inflate(R.layout.my_row_order, parent, false);
        return new AdapterOreder.ViewHolderOreder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOreder holder, int position) {
        ModelOrder order = orderList.get(position);
         holder.name.setText(order.getName());
        holder.price.setText(order.getPrice());
        holder.count.setText(order.getCount());
        holder.color.setText(order.getColor());
        holder.id.setText(order.getId());
        holder.wight.setText(order.getWight());
        Glide.with(cont)
              .load(order.getImage())
                .into(holder.imageView);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(cont);
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data cant be undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("order")
                                 .child(root.getRef().getKey())
                                .removeValue();
                        Toast.makeText(cont ,"Cancelled" ,Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(cont ,"Cancelled" ,Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        holder.updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                update();
            }
        });

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void update() {

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolderOreder extends RecyclerView.ViewHolder {
        TextView name, color, count , id , wight , price;
        ImageView imageView;
        LinearLayout delete , updata ,item;

        public ViewHolderOreder(@NonNull View itemView) {
            super(itemView);

            name =(TextView) itemView.findViewById(R.id.name_order);
            color =(TextView) itemView.findViewById(R.id.Color_order);
            count =(TextView) itemView.findViewById(R.id.count_order);
            id =(TextView) itemView.findViewById(R.id.number_order);
            wight =(TextView) itemView.findViewById(R.id.wight_order);
            price =(TextView) itemView.findViewById(R.id.price_order);
            imageView =(ImageView) itemView.findViewById(R.id.image_order);
            updata =(LinearLayout) itemView.findViewById(R.id.update_elment);
            delete =(LinearLayout) itemView.findViewById(R.id.delet_elment);
            item =(LinearLayout) itemView.findViewById(R.id.item);

        }
    }
}
