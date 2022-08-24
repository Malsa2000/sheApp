package com.example.sheapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;

public class HomeAto extends AppCompatActivity {


    CheckBox enable , visable ;
    TextView bluttothDevice;
    ImageView imgSersh;
    ListView listView ;
EditText text ;
Button submit;
String number;

    private  BluetoothAdapter BA;
    private Set<BluetoothDevice> paridDevice;

    SendRisive sendRisive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ato);

        enable = (CheckBox) findViewById(R.id.enable);
        visable = (CheckBox) findViewById(R.id.visble);
        bluttothDevice = (TextView) findViewById(R.id.name_bluttoth);
        imgSersh = (ImageView) findViewById(R.id.sresh_bt);
        listView = (ListView) findViewById(R.id.bluttotn_device);

        text = (EditText) findViewById(R.id.text);
        submit = findViewById(R.id.submit);

        number =text.getText().toString();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // back...........................
               sendRisive.write(number.getBytes());

            }
        });

        BA = BluetoothAdapter.getDefaultAdapter();
        if(BA == null){
            Toast.makeText(this ,"" ,Toast.LENGTH_SHORT);
            finish();
        }

        if (BA.isEnabled()){
            enable.setChecked(true);
        }

        bluttothDevice.setText(getName());

        enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(! b){
                    BA.disable();
                    Toast.makeText(getApplicationContext() ,"Turren OFF" ,Toast.LENGTH_SHORT);

                }
                else{
                    Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(i ,0);
                    Toast.makeText(getApplicationContext() ,"Turren ON" ,Toast.LENGTH_SHORT);

                }
            }
        });

        visable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(i ,0);
                }
            }
        });

        imgSersh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list();
            }
        });
    }

    private void list() {

        paridDevice = BA.getBondedDevices();
        ArrayList arrayList = new ArrayList();
        for(BluetoothDevice bt : paridDevice){
            arrayList.add(bt.getName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
       listView.setAdapter(arrayAdapter);

    }

    public  String getName(){
        if(BA ==null){
            BA=BluetoothAdapter.getDefaultAdapter();
        }
        String name  = BA.getName();
        if(name == null){
            name  = BA.getAddress();
        }
        return name;
    }
}


 class SendRisive{
    private final BluetoothSocket bluetoothSocket;
     private final InputStream inputStream;
     private final OutputStream outputStream;


     SendRisive(BluetoothSocket bluetoothSocket) {
         this.bluetoothSocket = bluetoothSocket;
         InputStream tempin = null;
         OutputStream tempout = null;


         try{
             tempin = bluetoothSocket.getInputStream();
             tempout = bluetoothSocket.getOutputStream();
         } catch (Exception e) {
             e.printStackTrace();
         }
         inputStream = tempin;
         outputStream = tempout;
     }

     public void write(byte[] bytes){
         try {
             outputStream.write(bytes);

         } catch (Exception e) {
             e.printStackTrace();
         }
     }
 }