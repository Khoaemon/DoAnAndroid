package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangNhapActivity extends AppCompatActivity {

    Button btnDangNhap, btnDangKy;
    EditText edtTenDangNhap, edtMatKhau;
    Database v_dtb;
    private SharedPreferences v_taikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        AnhXa();

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean flag = false;

                if(edtTenDangNhap.getText().toString().equals("")||edtMatKhau.getText().toString().equals("")){
                    Toast.makeText(DangNhapActivity.this, "Vui lòng điền thông tin đăng nhập!", Toast.LENGTH_SHORT).show();
                }else{
                    flag = v_dtb.KiemTraDangNhap(edtTenDangNhap.getText().toString().toLowerCase(), edtMatKhau.getText().toString());
                }

                if(flag == true){
                    SharedPreferences.Editor v_editor = v_taikhoan.edit();
                    v_editor.putString("taikhoan", edtTenDangNhap.getText().toString());
                    v_editor.commit();
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
            }
        });
    }

    private void AnhXa() {
        btnDangNhap = (Button) findViewById(R.id.buttonLogin);
        btnDangKy = (Button) findViewById(R.id.buttonSignUp);
        edtTenDangNhap = (EditText) findViewById(R.id.editTextUserName);
        edtMatKhau = (EditText) findViewById(R.id.editTextPassword);
        v_dtb = new Database(this);
        v_taikhoan = getSharedPreferences("taikhoan", MODE_PRIVATE);
    }
}