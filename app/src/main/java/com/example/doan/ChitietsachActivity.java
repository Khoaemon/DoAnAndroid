package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChitietsachActivity extends AppCompatActivity {

    TextView tensach, giaban, mota;
    ImageView hinhanh;
    Button themgiohang, xembinhluan;
    Sach v_sach;
    Intent v_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsach);

        AnhXa();

        v_sach = (Sach) v_intent.getSerializableExtra("sach");
        tensach.setText(v_sach.getTenSach().toString());
        mota.setText(v_sach.getMoTa().toString());
        giaban.setText(v_sach.getGia()+"");
        hinhanh.setImageResource(v_sach.getImgURL());

        xembinhluan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChitietsachActivity.this, BinhLuanActivity.class);
                intent.putExtra("masach", v_sach.getMaSach());
                startActivity(intent);
            }
        });

        themgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChitietsachActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AnhXa() {
        tensach = (TextView) findViewById(R.id.textViewTenSach);
        giaban = (TextView) findViewById(R.id.textViewGia);
        mota = (TextView) findViewById(R.id.textViewMoTa);
        hinhanh = (ImageView) findViewById(R.id.imgChiTietSach);
        themgiohang = (Button) findViewById(R.id.buttonThem);
        xembinhluan = (Button) findViewById(R.id.buttonXemBinhLuan);
        v_intent = getIntent();
    }
}