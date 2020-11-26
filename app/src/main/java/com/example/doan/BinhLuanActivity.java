package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BinhLuanActivity extends AppCompatActivity {

    EditText noidungbinhluan;
    Button thembinhluan;
    BinhLuanAdapter v_adapter;
    ListView lvBinhLuan;
    Intent v_intent;
    ArrayList<BinhLuan> v_binhLuanArrayList;
    Database v_dtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan);

        AnhXa();

        //binhLuanArrayList.add(new BinhLuan("Anh Khoa","Test bình luận"));

        v_adapter.notifyDataSetChanged();
        lvBinhLuan.setAdapter(v_adapter);

    }

    private void AnhXa() {
        noidungbinhluan = (EditText) findViewById(R.id.editTextBinhLuan);
        thembinhluan = (Button) findViewById(R.id.buttonThemBinhLuan);
        lvBinhLuan = (ListView) findViewById(R.id.listviewBinhLuan);
        v_intent = getIntent();
        v_dtb = new Database(this);
        v_binhLuanArrayList = new ArrayList<>();
        v_binhLuanArrayList = v_dtb.LayDanhSachBinhLuan(v_intent.getIntExtra("masach",1234));
        v_adapter = new BinhLuanAdapter(BinhLuanActivity.this, R.layout.dong_binh_luan, v_binhLuanArrayList);
    }
}