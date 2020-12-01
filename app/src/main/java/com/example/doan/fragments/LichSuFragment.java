package com.example.doan.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.BinhLuanActivity;
import com.example.doan.ChiTietHoaDonActivity;
import com.example.doan.ChitietsachActivity;
import com.example.doan.Database;
import com.example.doan.HoaDon;
import com.example.doan.HoaDonAdapter;
import com.example.doan.R;

import java.util.ArrayList;


public class LichSuFragment extends Fragment {

    private ListView hoadonListView;
    private HoaDonAdapter v_adapter;
    private SharedPreferences v_taikhoan;
    private Database v_dtb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v_taikhoan = getContext().getSharedPreferences("taikhoan", Context.MODE_PRIVATE);
        if(v_taikhoan.getString("taikhoan","").equals("")){
            return inflater.inflate(R.layout.fragment_not_available, container, false);
        }

        View view = inflater.inflate(R.layout.fragment_lich_su, container, false);

        AnhXa(view);

        hoadonListView.setAdapter(v_adapter);


        hoadonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String v_mahd = ((TextView) view.findViewById(R.id.textViewMaHD)).getText().toString();
                Intent intent = new Intent(getContext(), ChiTietHoaDonActivity.class);
                intent.putExtra("mahoadon", v_mahd);
                startActivity(intent);
            }
        });

        return view;
    }

    private void AnhXa(View view) {
        hoadonListView = view.findViewById(R.id.lvHoaDon);
        v_dtb = new Database(getContext());
        v_adapter = new HoaDonAdapter(getContext(), R.layout.dong_hoa_don, v_dtb.LayDanhSachHoaDon(v_taikhoan.getString("taikhoan","")));
    }
}