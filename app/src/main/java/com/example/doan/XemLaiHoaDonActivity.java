package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.fragments.SachFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class XemLaiHoaDonActivity extends AppCompatActivity {

    private ListView lvXemLaiHD;
    private TextView tvTong;
    private RadioGroup radioGroup;
    private RadioButton rbTuNhap, rbLayTuTaiKhoan;
    private EditText edtHoTen, edtSDT, edtDiaChi, edtGhiChu;
    private Button btnXacNhan;
    private ArrayList<String> v_hoadon;
    private ArrayList<String> v_ttgh;
    private ArrayList<GioHang> giohangArrayList;
    private ArrayAdapter v_adapter;
    private SharedPreferences v_giohang;
    private SharedPreferences v_taikhoan;
    private Gson v_gson;
    private String v_json;
    private Type v_type;
    private Database v_dtb;
    private double v_tongtien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_lai_hoa_don);

        AnhXa();

        for(int i = 0; i < giohangArrayList.size(); i++){
            String v_tensach = giohangArrayList.get(i).getTenSach();
            String v_soluong = giohangArrayList.get(i).getSoLuong()+"";
            double v_gia = giohangArrayList.get(i).getSoLuong()*giohangArrayList.get(i).getGia();
            v_hoadon.add(v_soluong+" cuốn '"+v_tensach+"' với giá là: "+ NumberFormat.getNumberInstance(Locale.US).format(v_gia)+" VNĐ.");
            v_tongtien += giohangArrayList.get(i).getGia()*giohangArrayList.get(i).getSoLuong();
        }

        tvTong.setText(NumberFormat.getNumberInstance(Locale.US).format(v_tongtien)+" VNĐ");
        lvXemLaiHD.setAdapter(v_adapter);

        if(v_taikhoan.getString("taikhoan","").equals("")){
            rbLayTuTaiKhoan.setVisibility(View.GONE);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rbLayTuTaiKhoan.isChecked()){
                    ArrayList<String> v_thongtincanhan = v_dtb.ThongTinCaNhan(v_taikhoan.getString("taikhoan",""));
                    edtHoTen.setText(v_thongtincanhan.get(1));
                    edtSDT.setText(v_thongtincanhan.get(4));
                    edtDiaChi.setText(v_thongtincanhan.get(3));
                }else if(rbTuNhap.isChecked()){
                    edtHoTen.setText("");
                    edtSDT.setText("");
                    edtDiaChi.setText("");
                }
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(
                        edtHoTen.getText().toString().equals("")
                        ||edtSDT.getText().toString().equals("")
                        ||edtDiaChi.getText().toString().equals("")
                        ||edtGhiChu.getText().toString().equals("")
                ){
                    Toast.makeText(XemLaiHoaDonActivity.this, "Thông tin giao hàng không thể để trống!", Toast.LENGTH_SHORT).show();
                }else{
                    v_ttgh.add(edtHoTen.getText().toString());
                    v_ttgh.add(edtSDT.getText().toString());
                    v_ttgh.add(edtDiaChi.getText().toString());
                    v_ttgh.add(edtGhiChu.getText().toString());
                    int v_maTTGH = v_dtb.ThemTTGH(v_ttgh);
                    v_dtb.ThemHoaDon(v_taikhoan.getString("taikhoan",""), giohangArrayList, v_tongtien, v_maTTGH);
                    giohangArrayList.clear();
                    v_adapter.notifyDataSetChanged();
                    Toast.makeText(XemLaiHoaDonActivity.this, "Cảm ơn bạn đã mua hàng!", Toast.LENGTH_SHORT).show();
                    UpdatePreferenceFile();
                    finish();
                }
            }
        });
    }

    public void UpdatePreferenceFile(){
        SharedPreferences.Editor v_editor = v_giohang.edit();
        String v_json = v_gson.toJson(giohangArrayList, v_type);
        v_editor.putString("giohang", v_json);
        v_editor.apply();
    }

    private void AnhXa() {
        lvXemLaiHD = (ListView) findViewById(R.id.lvXemLaiHD);
        tvTong = (TextView) findViewById(R.id.tvTong);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rbTuNhap = (RadioButton) findViewById(R.id.radioTuNhap);
        rbLayTuTaiKhoan = (RadioButton) findViewById(R.id.radioLayTuTaiKhoan);
        edtHoTen = (EditText) findViewById(R.id.editTextHoTenNhanHang);
        edtSDT = (EditText) findViewById(R.id.editTextSDTNhanHang);
        edtDiaChi = (EditText) findViewById(R.id.editTextDiaChiNhanHang);
        edtGhiChu = (EditText) findViewById(R.id.editTextGhiChuNhanHang);
        btnXacNhan = (Button) findViewById(R.id.btnXacNhanDonHang);
        v_hoadon = new ArrayList<>();
        v_ttgh = new ArrayList<>();
        v_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, v_hoadon);
        v_giohang = getSharedPreferences("giohang", Context.MODE_PRIVATE);
        v_taikhoan = getSharedPreferences("taikhoan", Context.MODE_PRIVATE);
        v_type = new TypeToken<ArrayList<GioHang>>(){}.getType();
        v_gson = new Gson();
        v_json = v_giohang.getString("giohang","");
        giohangArrayList = v_gson.fromJson(v_json, v_type);
        v_dtb = new Database(this);
    }
}