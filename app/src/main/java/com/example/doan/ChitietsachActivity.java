package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChitietsachActivity extends AppCompatActivity {

    TextView tensach, giaban, mota;
    ImageView hinhanh;
    Button themgiohang, xembinhluan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsach);

        AnhXa();

        xembinhluan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChitietsachActivity.this, BinhLuanActivity.class));
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
    }
}