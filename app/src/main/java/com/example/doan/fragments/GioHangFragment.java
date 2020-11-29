package com.example.doan.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.GioHangAdapter;
import com.example.doan.R;
import com.example.doan.Sach;

import java.util.ArrayList;


public class GioHangFragment extends Fragment implements GioHangAdapter.EventListener{

    ListView lvGioHang;
    TextView thanhtien;
    Button btnThanhToan;
    ArrayList<Sach> giohangArrayList;
    GioHangAdapter v_adapter;
    private SharedPreferences v_taikhoan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v_taikhoan = getContext().getSharedPreferences("taikhoan", Context.MODE_PRIVATE);
        if(v_taikhoan.getString("taikhoan","").equals("")){
            return inflater.inflate(R.layout.fragment_not_available, container, false);
        }

        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);

        AnhXa(view);

        //giohangArrayList.add(new Sach(1, "Lập trình Android từ cơ bản đến nâng cao",0,"", 120000, 2));

        lvGioHang.setAdapter(v_adapter);

        return view;
    }

    private void AnhXa(View view) {
        lvGioHang = (ListView) view.findViewById(R.id.listviewGioHang);
        thanhtien = (TextView) view.findViewById(R.id.textViewThanhTien);
        btnThanhToan = (Button) view.findViewById(R.id.buttonThanhToan);
        giohangArrayList = new ArrayList<>();
        v_adapter = new GioHangAdapter(getContext(), R.layout.san_pham_gio_hang, giohangArrayList, this);
    }

    public void DialogXoaGioHang(String tensach){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(getContext());
        dialogXoa.setMessage("Bạn có muốn xóa cuốn '"+tensach+"' không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Xóa thành công.", Toast.LENGTH_SHORT).show();
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