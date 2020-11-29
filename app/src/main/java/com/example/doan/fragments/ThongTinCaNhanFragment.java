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
import android.widget.Toast;

import com.example.doan.R;

public class ThongTinCaNhanFragment extends Fragment {

    private SharedPreferences v_taikhoan;
    Menu menu;
    Button btnDangXuat;

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
    }
}