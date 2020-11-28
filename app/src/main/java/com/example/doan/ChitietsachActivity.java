package com.example.doan;

import androidx.annotation.RequiresApi;
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

    TextView tensach, giaban, mota, tacgia, theloai;
    ImageView hinhanh;
    Button themgiohang, xembinhluan;
    Sach v_sach;
    Intent v_intent;
    Database v_dtb;
    ArrayList<String> v_theloaiArray;
    ArrayList<String> v_tacgiaArray;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsach);

        AnhXa();

        v_sach = (Sach) v_intent.getSerializableExtra("sach");

        v_tacgiaArray = v_dtb.LayTacGiaSach(v_sach.getMaSach());
        v_theloaiArray = v_dtb.LayTheLoaiSach(v_sach.getMaSach());

        flag = v_tacgiaArray.size() - 1;
        for(int i = 0; i < v_tacgiaArray.size(); i++){
            if(i == flag){
                tacgia.append(v_tacgiaArray.get(i)+")");
            }else{
                tacgia.append(v_tacgiaArray.get(i)+", ");
            }
        }

        flag = v_theloaiArray.size() - 1;
        for(int i = 0; i < v_theloaiArray.size(); i++){
            if(i == flag){
                theloai.append(v_theloaiArray.get(i)+".");
            }else{
                theloai.append(v_theloaiArray.get(i)+", ");
            }
        }

        tensach.setText(v_sach.getTenSach().toString());
        mota.setText(v_sach.getMoTa().toString());
        giaban.setText(v_sach.getGia()+" VNĐ");
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
        tensach.setSelected(true);
        giaban = (TextView) findViewById(R.id.textViewGia);
        mota = (TextView) findViewById(R.id.textViewMoTa);
        tacgia = (TextView) findViewById(R.id.textViewTacGia);
        theloai = (TextView) findViewById(R.id.textViewTheLoaiSach);
        hinhanh = (ImageView) findViewById(R.id.imgChiTietSach);
        themgiohang = (Button) findViewById(R.id.buttonThem);
        xembinhluan = (Button) findViewById(R.id.buttonXemBinhLuan);
        v_intent = getIntent();
        v_dtb = new Database(this);
        v_tacgiaArray = new ArrayList<>();
        v_theloaiArray = new ArrayList<>();
    }
}