package com.example.doan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.fragments.GioHangFragment;
import com.example.doan.fragments.LichSuFragment;
import com.example.doan.fragments.SachFragment;

import java.util.ArrayList;

public class ChiTietHoaDonActivity extends AppCompatActivity {

    private ListView hoadonListView;
    private TextView tvTen, tvSDT, tvDiaChi, tvGhiChu;
    private CTHDAdapter v_adapter;
    private Intent v_intent;
    private Database v_dtb;
    private Button btnHuyDon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);

        AnhXa();

        hoadonListView.setAdapter(v_adapter);
        ArrayList<String> a = v_dtb.LayDanhSachTTGH(v_intent.getStringExtra("mahoadon"));
        tvTen.setText(" "+a.get(0));
        tvSDT.setText(" "+a.get(1));
        tvDiaChi.setText(" "+a.get(2));
        tvGhiChu.setText(" "+a.get(3));

        btnHuyDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogXoaGioHang();
            }
        });

        //cthdArrayList.add(new CTHD(1, "Học lập trình Android từ cơ bản đến nâng cao",2, 150000));

    }

    public void DialogXoaGioHang(){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn hủy hóa đơn này không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                v_dtb.XoaHoaDon(v_intent.getStringExtra("mahoadon"));
                Toast.makeText(ChiTietHoaDonActivity.this, "Hủy Thành Công!", Toast.LENGTH_SHORT).show();
                finish();
                //getSupportFragmentManager().beginTransaction().replace(R.id.frlayout,new LichSuFragment()).commit();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }

    private void AnhXa() {
        hoadonListView = findViewById(R.id.lvCTHD);
        tvTen = findViewById(R.id.textViewTenNguoiNhanHang);
        tvSDT = findViewById(R.id.textViewSDTNhanHang);
        tvDiaChi = findViewById(R.id.textViewDiaChiNhanHang);
        tvGhiChu = findViewById(R.id.textViewGhiChu);
        btnHuyDon = findViewById(R.id.btnHuyDon);
        v_intent = getIntent();
        v_dtb = new Database(this);
        v_adapter = new CTHDAdapter(this, R.layout.dong_chi_tiet_hoa_don, v_dtb.LayDanhSachCTHD(v_intent.getStringExtra("mahoadon")));
    }
}