package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ChiTietHoaDonActivity extends AppCompatActivity {

    ListView hoadonListView;
    ArrayList<CTHD> cthdArrayList;
    CTHDAdapter v_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);

        AnhXa();

        hoadonListView.setAdapter(v_adapter);

        cthdArrayList.add(new CTHD(1, "Học lập trình Android từ cơ bản đến nâng cao",2, 150000));

    }

    private void AnhXa() {
        hoadonListView = findViewById(R.id.lvCTHD);
        cthdArrayList = new ArrayList<>();
        v_adapter = new CTHDAdapter(this, R.layout.dong_chi_tiet_hoa_don, cthdArrayList);
    }
}