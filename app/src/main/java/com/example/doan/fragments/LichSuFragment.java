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

import com.example.doan.ChiTietHoaDonActivity;
import com.example.doan.HoaDon;
import com.example.doan.HoaDonAdapter;
import com.example.doan.R;

import java.util.ArrayList;


public class LichSuFragment extends Fragment {

    ListView hoadonListView;
    ArrayList<HoaDon> hoaDonArrayList;
    HoaDonAdapter v_adapter;
    private SharedPreferences v_taikhoan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v_taikhoan = getContext().getSharedPreferences("taikhoan", Context.MODE_PRIVATE);
        if(v_taikhoan.getString("taikhoan","").equals("")){
            return inflater.inflate(R.layout.fragment_not_available, container, false);
        }

        View view = inflater.inflate(R.layout.fragment_lich_su, container, false);

        AnhXa(view);

        hoadonListView.setAdapter(v_adapter);

        hoaDonArrayList.add(new HoaDon(1, "01/01/2001", 100000));
        hoaDonArrayList.add(new HoaDon(2, "02/02/2002", 200000));
        hoaDonArrayList.add(new HoaDon(3, "03/03/2003", 300000));

        hoadonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getContext(), ChiTietHoaDonActivity.class));
            }
        });

        return view;
    }

    private void AnhXa(View view) {
        hoadonListView = view.findViewById(R.id.lvHoaDon);
        hoaDonArrayList = new ArrayList<>();
        v_adapter = new HoaDonAdapter(getContext(), R.layout.dong_hoa_don, hoaDonArrayList);
    }
}