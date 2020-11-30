package com.example.doan.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.Database;
import com.example.doan.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ThongTinCaNhanFragment extends Fragment {

    private SharedPreferences v_taikhoan;
    private TextView tvMaNguoiDung, tvTenNguoiDung, tvTenDangNhap, tvSDT, tvDiaChi;
    private EditText edtMatKhauCu, edtMatKhauMoi1, edtMatKhauMoi2;
    private Menu menu;
    private Button btnDangXuat, btnDoiMatKhau;
    private Database v_dtb;
    private ArrayList<String> info;

    public ThongTinCaNhanFragment(Menu menu) {
        this.menu = menu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v_taikhoan = getContext().getSharedPreferences("taikhoan", Context.MODE_PRIVATE);
        if(v_taikhoan.getString("taikhoan","").equals("")){
            return inflater.inflate(R.layout.fragment_not_available, container, false);
        }

        final View view = inflater.inflate(R.layout.fragment_thong_tin_ca_nhan, container, false);

        AnhXa(view);

        info = v_dtb.ThongTinCaNhan(v_taikhoan.getString("taikhoan",""));
        tvMaNguoiDung.setText(" "+info.get(0));
        tvTenNguoiDung.setText(" "+info.get(1));
        tvTenDangNhap.setText(" "+info.get(2));
        tvDiaChi.setText(" "+info.get(3));
        tvSDT.setText(" "+info.get(4));

        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtMatKhauCu.getText().toString().equals("")||edtMatKhauMoi1.getText().toString().equals("")||edtMatKhauMoi2.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Thông tin không được để trống!", Toast.LENGTH_SHORT).show();
                }else{
                    if(v_dtb.KiemTraMatKhauCu(v_taikhoan.getString("taikhoan",""), edtMatKhauCu.getText().toString())){
                        if(edtMatKhauMoi1.getText().toString().equals(edtMatKhauMoi2.getText().toString())){
                            v_dtb.DoiMatKhau(v_taikhoan.getString("taikhoan",""), edtMatKhauMoi2.getText().toString());
                            edtMatKhauCu.setText("");
                            edtMatKhauMoi1.setText("");
                            edtMatKhauMoi2.setText("");
                            Toast.makeText(getContext(), "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Mật khẩu nhập lại không đúng!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "Mật khẩu cũ không đúng!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor v_editor = v_taikhoan.edit();
                v_editor.clear();
                v_editor.commit();
                Toast.makeText(view.getContext(), "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();
                MenuItem item = menu.findItem(R.id.menuDangNhap);
                item.setVisible(true);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frlayout,new SachFragment()).commit();
            }
        });

        return view;
    }

    private void AnhXa(View view) {
        btnDangXuat = (Button) view.findViewById(R.id.btnDangXuat);
        btnDoiMatKhau = (Button) view.findViewById(R.id.buttonDoiMatKhau);
        tvMaNguoiDung = (TextView) view.findViewById((R.id.textViewMaNguoiDung));
        tvTenNguoiDung = (TextView) view.findViewById((R.id.textViewTenNguoiDung));
        tvTenDangNhap = (TextView) view.findViewById((R.id.textViewTenDangNhap));
        tvDiaChi = (TextView) view.findViewById((R.id.textViewDiaChi));
        tvSDT = (TextView) view.findViewById((R.id.textViewSDT));
        edtMatKhauCu = (EditText) view.findViewById(R.id.editTextMatKhauCu);
        edtMatKhauMoi1 = (EditText) view.findViewById(R.id.editTextMatKhauMoi);
        edtMatKhauMoi2 = (EditText) view.findViewById(R.id.editTextMatKhauMoi2);
        v_dtb = new Database(getContext());
        info = new ArrayList<>();
    }
}