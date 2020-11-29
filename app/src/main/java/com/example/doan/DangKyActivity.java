package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DangKyActivity extends AppCompatActivity {

    Button btnDangKy;
    EditText edtHoTen, edtTenDangNhap, edtMatKhau1, edtMatKhau2, edtDiaChi, edtSDT;
    Database v_dtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        AnhXa();

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(
                        edtHoTen.getText().toString().equals("")
                        ||edtTenDangNhap.getText().toString().equals("")
                        ||edtMatKhau1.getText().toString().equals("")
                        ||edtMatKhau2.getText().toString().equals("")
                        ||edtDiaChi.getText().toString().equals("")
                        ||edtSDT.getText().toString().equals("")){
                    Toast.makeText(DangKyActivity.this, "Thông tin không được để trống!", Toast.LENGTH_SHORT).show();
                }else if(v_dtb.KiemTraTonTaiTaiKhoan(edtTenDangNhap.getText().toString().toLowerCase())){
                    Toast.makeText(DangKyActivity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                }else if(edtMatKhau1.getText().toString().equals(edtMatKhau2.getText().toString()) != true){
                    Toast.makeText(DangKyActivity.this, "Mật khẩu nhập lại không đúng!", Toast.LENGTH_SHORT).show();
                }else if(edtSDT.length()!=10){
                    Toast.makeText(DangKyActivity.this, "Số điện thoại phải là 10 ký tự!", Toast.LENGTH_SHORT).show();
                } else{
                    ArrayList<String> a = new ArrayList<>();
                    a.add(edtHoTen.getText().toString());
                    a.add(edtTenDangNhap.getText().toString());
                    a.add(edtMatKhau1.getText().toString());
                    a.add(edtDiaChi.getText().toString());
                    a.add(edtSDT.getText().toString());
                    v_dtb.DangKyTaiKhoan(a);
                    Toast.makeText(DangKyActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    edtHoTen.setText("");
                    edtTenDangNhap.setText("");
                    edtMatKhau1.setText("");
                    edtMatKhau2.setText("");
                    edtDiaChi.setText("");
                    edtSDT.setText("");
                    finish();
                }
            }
        });
    }

    private void AnhXa() {
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        edtHoTen = (EditText) findViewById(R.id.editTextHoTen);
        edtTenDangNhap = (EditText) findViewById(R.id.editTextTenDangNhap);
        edtMatKhau1 = (EditText) findViewById(R.id.editTextMatKhau1);
        edtMatKhau2 = (EditText) findViewById(R.id.editTextMatKhau2);
        edtDiaChi = (EditText) findViewById(R.id.editTextDiaChi);
        edtSDT = (EditText) findViewById(R.id.editTextSDT);
        v_dtb = new Database(this);
    }
}