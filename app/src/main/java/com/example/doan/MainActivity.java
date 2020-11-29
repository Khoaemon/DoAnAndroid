package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
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

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String DB_PATH = "data/data/com.example.doan/databases/bansach.sqlite";
    private SharedPreferences v_taikhoan;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //doDBCheck();
        v_taikhoan = getSharedPreferences("taikhoan", MODE_PRIVATE);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frlayout,new SachFragment()).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(menu!=null && v_taikhoan.getString("taikhoan","").equals("")){
            MenuItem item = menu.findItem(R.id.menuDangNhap);
            item.setVisible(true);
        }else if (menu!=null && v_taikhoan.getString("taikhoan","").equals("")==false){
            MenuItem item = menu.findItem(R.id.menuDangNhap);
            item.setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.dang_nhap, menu);
        if(v_taikhoan.getString("taikhoan","").equals("")){
            return super.onCreateOptionsMenu(menu);
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuDangNhap){
            startActivity(new Intent(this, DangNhapActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean ReplaceFragment(MenuItem item){
        FragmentManager v_fragmentManager = getSupportFragmentManager();
        FragmentTransaction v_fragmentTransaction = v_fragmentManager.beginTransaction();
        Fragment v_fragment = null;
        switch (item.getItemId()){
            case R.id.book: v_fragment = new SachFragment();
                break;
            case R.id.personalinfo: v_fragment = new ThongTinCaNhanFragment(menu);
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

    /*private void doDBCheck()
    {
        try{
            File file = new File(DB_PATH);
            file.delete();
        }catch(Exception ex)
        {}
    }*/

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