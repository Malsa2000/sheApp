package com.example.sheapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.sheapp.MainActivity;
import com.example.sheapp.R;
import com.example.sheapp.width.goodWidth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminAddNewContent extends AppCompatActivity {

    EditText title, description, compont;
    LinearLayout TypeSkinCare , TypeBodyCare , TypeHiarCare;
    ListView listView;
    ArrayList<String> itemArray;
    Button addButton, loadContent;
    ImageView imageMask;
    Uri imageUri;
    CheckBox skinCare, HairCare, BodyCare;
    private static final int PICK_IMAGE_REQUEST = 1;
    String titleString, descString, item;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Care");
    StorageReference reference = FirebaseStorage.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_content);

        //declear varible :
        daclearVarible();

        if(skinCare.isChecked()){
            TypeHiarCare.setVisibility(View.VISIBLE);
            TypeSkinCare.setVisibility(View.INVISIBLE);
            TypeBodyCare.setVisibility(View.INVISIBLE);
        }

      else if(HairCare.isChecked()){
            TypeHiarCare.setVisibility(View.VISIBLE);
            TypeSkinCare.setVisibility(View.INVISIBLE);
            TypeBodyCare.setVisibility(View.INVISIBLE);


        }
      else if(BodyCare.isChecked()){
            TypeBodyCare.setVisibility(View.VISIBLE);
        }

        //get image from galary
        imageMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImage();
            }
        });

        //load content in fireBase
        loadContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signinUser();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String item = compont.getText().toString();
                itemArray = new ArrayList<>();
                ArrayAdapter<String> adep = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, itemArray);
                listView.setAdapter(adep);
                adep.notifyDataSetChanged();

                int count = 0;
                for(int i = 0 ; i<10 ; i++){
                itemArray.add(count++ + " - " + item);}
                listView.setVisibility(View.VISIBLE);
            }
        });

    }

    private void daclearVarible()
    {

        loadContent = findViewById(R.id.lode_contentin_firebase);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        addButton = findViewById(R.id.add);
        compont = findViewById(R.id.compont);
        listView = findViewById(R.id.list_item);
        skinCare = findViewById(R.id.skin_care_add);
        HairCare = findViewById(R.id.hair_care_add);
        BodyCare = findViewById(R.id.body_care_add);
        imageMask = findViewById(R.id.image_mask);
        TypeBodyCare = findViewById(R.id.type_body);
        TypeSkinCare = findViewById(R.id.type_skin);
        TypeHiarCare = findViewById(R.id.type_hiar);

        //typeOfCare=new StringBuilder();
    }

    private void loadImage() {

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();

            Picasso.with(this).load(imageUri).into(imageMask);
        }
    }

    public void signinUser() {

        titleString = title.getText().toString().trim();
        descString = description.getText().toString().trim();
        item = compont.getText().toString().trim();

        ChecedEditText();

        root.setValue(titleString);
        HashMap<String, Object> usermape = new HashMap<>();
        usermape.put("MaskName", titleString);
        usermape.put("description", descString);
        usermape.put("NameOfcomponent", item);


        if (skinCare.isChecked()) {
            if (imageUri != null) {
                uplodeImageToFireBase(imageUri);
            } else {
                Toast.makeText(getApplicationContext(), "you dont select image!", Toast.LENGTH_SHORT).show();

            }

            root.child("SkinCare").push().updateChildren(usermape).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    Toast.makeText(getApplicationContext(), "add mask Succsess", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), goodWidth.class);
                    startActivity(i);

                }
            });


        }
    }

    private void ChecedEditText() {
        if (titleString.isEmpty()) {
            title.setError("full the fild");
            title.requestFocus();
            return;
        }
        if (descString.isEmpty()) {
            description.setError("full the fild");
            description.requestFocus();
            return;
        }
        if (item.isEmpty()) {
            compont.setError("full the fild");
            compont.requestFocus();
            return;
        }
    }

    private void uplodeImageToFireBase(Uri uri) {
        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtention(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                Model model = new Model(uri.toString());
//                String modelId = root.push().getKey();
//                root.child("SkinCare").child(titleString).child("Image").setValue(model);
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Uplode image filed!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private String getFileExtention(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }


}