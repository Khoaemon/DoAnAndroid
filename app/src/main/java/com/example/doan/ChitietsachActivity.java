package com.example.doan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

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
    private SharedPreferences v_giohang;
    private ArrayList<GioHang> v_gioHangArrayList;

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
        giaban.setText(NumberFormat.getNumberInstance(Locale.US).format(v_sach.getGia())+" VNĐ");
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
                Type v_type = new TypeToken<ArrayList<GioHang>>(){}.getType();
                Gson v_gson = new Gson();
                String v_json1 = v_giohang.getString("giohang","");
                v_gioHangArrayList = v_gson.fromJson(v_json1, v_type);
                Boolean flag = false;

                if (v_gioHangArrayList==null) {
                    v_gioHangArrayList = new ArrayList<>();
                    v_gioHangArrayList.add(new GioHang(v_sach.getMaSach(), 1));
                }else{
                    for(int i = 0; i < v_gioHangArrayList.size(); i++){
                        if(v_gioHangArrayList.get(i).getMaSach()==v_sach.getMaSach()){
                            int v_soluong = v_gioHangArrayList.get(i).getSoLuong();
                            v_gioHangArrayList.get(i).setSoLuong(++v_soluong);
                            flag = true;
                        }
                    }
                    if(flag==false){
                        v_gioHangArrayList.add(new GioHang(v_sach.getMaSach(), 1));
                    }
                }

                SharedPreferences.Editor v_editor = v_giohang.edit();
                String v_json2 = v_gson.toJson(v_gioHangArrayList, v_type);
                v_editor.putString("giohang", v_json2);
                v_editor.apply();
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
        
        v_giohang = getSharedPreferences("giohang", MODE_PRIVATE);
        v_gioHangArrayList = new ArrayList<>();
    }
}