package com.example.sheapp.Note;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapterNote extends RecyclerView.Adapter<MyAdapterNote.MyViewHolder> {

    Context cont;
    ArrayList<NoteModel> noteList;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Note");


    public MyAdapterNote(Context cont, ArrayList<NoteModel> noteList) {
        this.cont = cont;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(cont).inflate(R.layout.my_row_note, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        NoteModel note = noteList.get(position);

        holder.name.setText(note.getEventName());
        holder.date.setText(note.getDate());
        holder.description.setText(note.getEventDescription());

//        Glide.with(mContext)
//                .load(uploadcurrent.getSongImageUri())
//                .into(holder.image_view);

       holder.delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder builder = new AlertDialog.Builder(cont);
               builder.setTitle("Are you sure?");
               builder.setMessage("Deleted data cant be undo");
               builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       FirebaseDatabase.getInstance().getReference().child("Note")
                             //  .child(root.getRef(position).getKey())
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
   
    }

    private void update() {



    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, description, date ;
        Button delete , updata;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name =(TextView) itemView.findViewById(R.id.note_name_view);
            description = (TextView) itemView.findViewById(R.id.note_descraption_view);
            date = (TextView) itemView.findViewById(R.id.note_date_view);
            delete = (Button)itemView.findViewById(R.id.delet_note);
            updata = (Button)itemView.findViewById(R.id.update);

        }
    }
}
