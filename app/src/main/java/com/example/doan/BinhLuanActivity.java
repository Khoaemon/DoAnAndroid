package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class BinhLuanActivity extends AppCompatActivity {

    EditText noidungbinhluan;
    Button thembinhluan;
    ArrayList<BinhLuan> binhLuanArrayList;
    BinhLuanAdapter adapter;
    ListView lvBinhLuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan);

        AnhXa();

        binhLuanArrayList.add(new BinhLuan("Anh Khoa","Test bình luận"));

        lvBinhLuan.setAdapter(adapter);
    }

    private void AnhXa() {
        noidungbinhluan = (EditText) findViewById(R.id.editTextBinhLuan);
        thembinhluan = (Button) findViewById(R.id.buttonThemBinhLuan);
        binhLuanArrayList = new ArrayList<>();
        adapter = new BinhLuanAdapter(BinhLuanActivity.this, R.layout.dong_binh_luan, binhLuanArrayList);
        lvBinhLuan = (ListView) findViewById(R.id.listviewBinhLuan);
    }
}