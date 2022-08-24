package com.example.sheapp;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.sheapp.Note.NoteView;
import com.google.android.gms.common.internal.service.Common;

import androidx.appcompat.widget.AppCompatButton;

public class NetworkFaild extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View layout_dialog = LayoutInflater.from(context).inflate(R.layout.error_layout ,null);

        AppCompatButton retry = layout_dialog.findViewById(R.id.ok_button);
        builder.setView(layout_dialog);

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setGravity(Gravity.CENTER);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                Intent intent1 = new Intent(Settings.ACTION_WIFI_SETTINGS);
                context.startActivity(intent1);
                //onReceive(context ,intent);
            }
        });
    }
}
