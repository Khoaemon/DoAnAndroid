package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ChiTietHoaDonActivity extends AppCompatActivity {

    private ListView hoadonListView;
    private CTHDAdapter v_adapter;
    private Intent v_intent;
    private Database v_dtb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);

        AnhXa();

        hoadonListView.setAdapter(v_adapter);

        //cthdArrayList.add(new CTHD(1, "Học lập trình Android từ cơ bản đến nâng cao",2, 150000));

    }

    private void AnhXa() {
        hoadonListView = findViewById(R.id.lvCTHD);
        v_intent = getIntent();
        v_dtb = new Database(this);
        v_adapter = new CTHDAdapter(this, R.layout.dong_chi_tiet_hoa_don, v_dtb.LayDanhSachCTHD(v_intent.getStringExtra("mahoadon")));
    }
}