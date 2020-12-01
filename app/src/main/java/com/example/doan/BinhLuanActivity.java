package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BinhLuanActivity extends AppCompatActivity {

    EditText edtNoiDungBinhLuan;
    Button btnThemBinhLuan;
    BinhLuanAdapter v_adapter;
    ListView lvBinhLuan;
    Intent v_intent;
    Database v_dtb;
    private SharedPreferences v_taikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan);

        AnhXa();

        if(v_taikhoan.getString("taikhoan","").equals("")){
            edtNoiDungBinhLuan.setVisibility(View.GONE);
            btnThemBinhLuan.setVisibility(View.GONE);
        }else{
            edtNoiDungBinhLuan.setVisibility(View.VISIBLE);
            btnThemBinhLuan.setVisibility(View.VISIBLE);
        }

        //binhLuanArrayList.add(new BinhLuan("Anh Khoa","Test bình luận"));
        btnThemBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v_dtb.ThemBinhLuan(
                        v_taikhoan.getString("taikhoan",""),
                        v_intent.getIntExtra("masach",1234),
                        edtNoiDungBinhLuan.getText().toString());
                setAdapter();
                edtNoiDungBinhLuan.setText("");
            }
        });

        setAdapter();

    }

    private void setAdapter(){
        v_adapter = new BinhLuanAdapter(BinhLuanActivity.this, R.layout.dong_binh_luan, v_dtb.LayDanhSachBinhLuan(v_intent.getIntExtra("masach",1234)));
        v_adapter.notifyDataSetChanged();
        lvBinhLuan.setAdapter(v_adapter);
    }

    private void AnhXa() {
        edtNoiDungBinhLuan = (EditText) findViewById(R.id.editTextBinhLuan);
        btnThemBinhLuan = (Button) findViewById(R.id.buttonThemBinhLuan);
        lvBinhLuan = (ListView) findViewById(R.id.listviewBinhLuan);
        v_taikhoan = getSharedPreferences("taikhoan", MODE_PRIVATE);
        v_intent = getIntent();
        v_dtb = new Database(this);
    }
}