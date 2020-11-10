package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.doan.fragments.GioHangFragment;
import com.example.doan.fragments.LichSuFragment;
import com.example.doan.fragments.SachFragment;
import com.example.doan.fragments.ThongTinCaNhanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frlayout,new SachFragment()).commit();
        }
    }

    public boolean ReplaceFragment(MenuItem item){
        FragmentManager v_fragmentManager = getSupportFragmentManager();
        FragmentTransaction v_fragmentTransaction = v_fragmentManager.beginTransaction();
        Fragment v_fragment = null;
        switch (item.getItemId()){
            case R.id.book: v_fragment = new SachFragment();
                break;
            case R.id.personalinfo: v_fragment = new ThongTinCaNhanFragment();
                break;
            case R.id.cart: v_fragment = new GioHangFragment();
                break;
            case R.id.history: v_fragment = new LichSuFragment();
                break;
        }
        v_fragmentTransaction.replace(R.id.frlayout, v_fragment);
        v_fragmentTransaction.commit();
        return false;
    }

    /*private void botNavEvent(BottomNavigationView v_botNav) {
        v_botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.book:
                        break;
                    case R.id.personalinfo: break;
                    case R.id.cart: break;
                    case R.id.history: break;
                }
                return false;
            }
        });
    }*/

}