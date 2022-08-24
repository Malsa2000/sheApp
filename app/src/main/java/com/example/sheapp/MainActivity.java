package com.example.sheapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheapp.width.goodWidth;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    NavigationView nav;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar1);
        nav = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle togel = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.open, R.string.close);

        drawer.addDrawerListener(togel);
        togel.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {


                }

                return true;
            }
        });



        BottomNavigationView bottomnav = findViewById(R.id.bottom_nav);
        bottomnav.setOnNavigationItemSelectedListener(navlisener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contener, new FragmantMainActivity()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlisener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selected = null;
                    switch (item.getItemId()) {
                        case R.id.searsh:
                            Toast.makeText(getApplicationContext() , "malsa",Toast.LENGTH_SHORT).show();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contener,
                            selected).commit();
                    return true;
                }
            };
        @Override
        public void onBackPressed() {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();

            }
        }


}