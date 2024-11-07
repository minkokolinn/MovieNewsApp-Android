package com.example.admin.movienewsapp.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.admin.movienewsapp.R;
import com.example.admin.movienewsapp.fragments.LatestFrag;
import com.example.admin.movienewsapp.fragments.PopularFrag;
import com.example.admin.movienewsapp.fragments.TopRatedFrag;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnv=findViewById(R.id.bnv);
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.frag_holder,new TopRatedFrag()).commit();
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.top_rated){
                    FragmentManager fm=getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.frag_holder,new TopRatedFrag()).commit();
                }else if(item.getItemId()==R.id.popular){
                    FragmentManager fm=getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.frag_holder,new PopularFrag()).commit();
                }else {
                    FragmentManager fm=getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.frag_holder,new LatestFrag()).commit();
                }

                return true;
            }
        });
    }
}
