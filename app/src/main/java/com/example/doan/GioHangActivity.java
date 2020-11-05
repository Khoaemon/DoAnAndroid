package com.example.doan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {

    ListView lvGioHang;
    TextView thanhtien;
    Button btnThanhToan;
    ArrayList<Sach> giohangArrayList;
    GioHangAdapter v_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        AnhXa();
        giohangArrayList.add(new Sach(1, "Lập trình Android từ cơ bản đến nâng cao","","", 120000, 2));

        lvGioHang.setAdapter(v_adapter);
    }

    private void AnhXa() {
        lvGioHang = (ListView) findViewById(R.id.listviewGioHang);
        thanhtien = (TextView) findViewById(R.id.textViewThanhTien);
        btnThanhToan = (Button) findViewById(R.id.buttonThanhToan);
        giohangArrayList = new ArrayList<>();
        v_adapter = new GioHangAdapter(GioHangActivity.this, R.layout.san_pham_gio_hang, giohangArrayList);
    }

    public void DialogXoaGioHang(String tensach){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa cuốn '"+tensach+"' không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(GioHangActivity.this, "Xóa thành công.", Toast.LENGTH_SHORT).show();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
}