package com.example.sheapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sheapp.Admin.Model;
import com.example.sheapp.SkinCare.SkinCare;
import com.example.sheapp.SkinCare.SkinType;
import com.example.sheapp.width.goodWidth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    EditText title, description, compont;
    ListView listView;
    ArrayList<String> itemArray;
    Button addButton, loadContent;
    ImageView imageMask;
    Uri imageUri;
    CheckBox skinCare, HairCare, BodyCare;
    ProgressDialog progressDialog;
    LinearLayout skin , hair , wether;

    RadioButton oilSkin , drySkin , normalSkin , sensitveSkin;
    RadioButton oilH , dryH , normalH , sensitveH;
    RadioButton hot , rain , oil ,normal;

    RadioGroup Rskin , Rhair ;

    private static final int PICK_IMAGE_REQUEST = 1;
    String titleString, descString, item;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Care");
    StorageReference reference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //declareVarible
        daclearVarible();



        addButton.setOnClickListener(this);
        loadContent.setOnClickListener(this);
        imageMask.setOnClickListener(this);


    }


    private void daclearVarible() {

        loadContent = (Button) findViewById(R.id.lode_contentin_firebase);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        addButton = (Button) findViewById(R.id.add);
        compont = findViewById(R.id.compont);
        listView = findViewById(R.id.list_item);
        imageMask = (ImageView) findViewById(R.id.image_mask);
        skinCare = findViewById(R.id.skin_care_add);
        HairCare = findViewById(R.id.hair_care_add);

        //LinerLayout
        skin = findViewById(R.id.skin);
        hair = findViewById(R.id.hair);
        wether = findViewById(R.id.wether);

        //RigButton hair
        oilH = findViewById(R.id.oil_hair_setting);
        dryH = findViewById(R.id.dry_haie_setting);
        normalH = findViewById(R.id.compnation_hair_setting);
        sensitveH = findViewById(R.id.sensitve_hair_setting);

        //RigButton skin
        oilSkin = findViewById(R.id.oil_skin);
        drySkin = findViewById(R.id.dry_skin);
        sensitveSkin = findViewById(R.id.sensetive_skin);
        normalSkin = findViewById(R.id.compnation_skin);

        //RigButton wether
        hot = findViewById(R.id.hot);
        rain = findViewById(R.id.rain);
        oil = findViewById(R.id.rotoba);
        normal = findViewById(R.id.normal);

        Rskin = findViewById(R.id.redio_hair);
        Rhair = findViewById(R.id.redio_skin);

        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                addItemInList();
                break;

            case R.id.lode_contentin_firebase:
                loadeContent(imageUri);
                break;

            case R.id.image_mask:
                loadImage();
                break;
        }
    }

    private void addItemInList() {

        String item = compont.getText().toString();
        itemArray = new ArrayList<>();
        ArrayAdapter<String> adep = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,
                itemArray);
        listView.setAdapter(adep);
        itemArray.add(item);
        listView.setVisibility(View.VISIBLE);
        adep.notifyDataSetChanged();

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

    public void loadeContent(Uri uri) {

        titleString = title.getText().toString().trim();
        descString = description.getText().toString().trim();
        item = compont.getText().toString().trim();

        if (!titleString.isEmpty() && !descString.isEmpty() && !item.isEmpty()) {

            checkBox();

            progressDialog.setTitle("Uploding ...");
            progressDialog.show();

            DatabaseReference f = root.child(checkBox()).child(titleString);

            StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtention(uri));
            fileRef.putFile(uri).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {


                    f.child("MaskName").setValue(titleString);
                    f.child("MaskDescription").setValue(descString);
                    f.child("Maskimage").setValue(task.getResult().toString());
                    f.child("Maskcontent").child("nameofComponent").setValue(item);



                    addButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            itemArray = new ArrayList<>();
                            ArrayAdapter<String> adep = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,
                                    itemArray);
                            listView.setAdapter(adep);
                            itemArray.add(item);
                            listView.setVisibility(View.VISIBLE);
                            adep.notifyDataSetChanged();

                            f.child("Maskcontent").child("nameofComponent").setValue(item);
                        }
                    });


                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Add mask Succsess", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), SkinCare.class);
                    startActivity(i);
                }
            });


        } else {
            Toast.makeText(getApplicationContext(), "dont add mask Succsess", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

        }

    }

    private String getFileExtention(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }


    private String checkBox() {
        String name = null;
        if (skinCare.isChecked()) {
            name = "SkinCare";
            skin.setVisibility(View.VISIBLE);
            DatabaseReference f = root.push().child(name);

        } else if (HairCare.isChecked()) {
            name = "HireCare";
            hair.setVisibility(View.VISIBLE);

            DatabaseReference f = root.push().child(name);
        } else if (BodyCare.isChecked()) {
            name = "BodyCare";
            wether.setVisibility(View.VISIBLE);
            DatabaseReference f = root.push().child(name);
        }

        return name;

    }


    public  void typeContent(){
    //    if(Rskin.getCheckedRadioButtonId() == R.id.oil_skin){


       // }
    }
}

