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

import com.example.doan.Database;
import com.example.doan.GioHang;
import com.example.doan.GioHangAdapter;
import com.example.doan.R;
import com.example.doan.Sach;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class GioHangFragment extends Fragment implements GioHangAdapter.EventListener{

    private ListView lvGioHang;
    private TextView tvThanhTien;
    private Button btnThanhToan;
    private ArrayList<GioHang> giohangArrayList;
    private GioHangAdapter v_adapter;
    private SharedPreferences v_giohang;
    private Gson v_gson;
    private String v_json;
    private Type v_type;
    private Database v_dtb;
    private double v_tongtien = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);

        AnhXa(view);


        //giohangArrayList.add(new Sach(1, "Lập trình Android từ cơ bản đến nâng cao",0,"", 120000, 2));
        if(giohangArrayList!=null){
            for(int i = 0; i < giohangArrayList.size(); i++){
                Sach v_sach = v_dtb.LayMotCuonSach(giohangArrayList.get(i).getMaSach());
                giohangArrayList.get(i).setTenSach(v_sach.getTenSach());
                giohangArrayList.get(i).setGia(v_sach.getGia());
                giohangArrayList.get(i).setImg(v_sach.getImgURL());
                v_tongtien += v_sach.getGia()*giohangArrayList.get(i).getSoLuong();
            }
        }

        SetTongTien(v_tongtien);

        v_adapter.notifyDataSetChanged();
        lvGioHang.setAdapter(v_adapter);

        return view;
    }

    private void AnhXa(View view) {
        lvGioHang = (ListView) view.findViewById(R.id.listviewGioHang);
        tvThanhTien = (TextView) view.findViewById(R.id.textViewThanhTien);
        btnThanhToan = (Button) view.findViewById(R.id.buttonThanhToan);
        v_giohang = getContext().getSharedPreferences("giohang", Context.MODE_PRIVATE);
        v_type = new TypeToken<ArrayList<GioHang>>(){}.getType();
        v_gson = new Gson();
        v_json = v_giohang.getString("giohang","");
        giohangArrayList = v_gson.fromJson(v_json, v_type);
        if(giohangArrayList==null){
            giohangArrayList = new ArrayList<>();
        }
        v_adapter = new GioHangAdapter(getContext(), R.layout.san_pham_gio_hang, giohangArrayList, this);
        v_dtb = new Database(getContext());
    }

    public void SetTongTien(double a){
        v_tongtien = a;
        tvThanhTien.setText(v_tongtien+" VNĐ");
    }

    public double GetTongTien(){
        return v_tongtien;
    }

    public void DialogXoaGioHang(final int v_masach, String v_tensach){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(getContext());
        dialogXoa.setMessage("Bạn có muốn xóa cuốn '"+v_tensach+"' khỏi giỏ hàng không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(int i = 0; i<giohangArrayList.size(); i++){
                    if(giohangArrayList.get(i).getMaSach()==v_masach){
                        v_tongtien -= giohangArrayList.get(i).getGia()*giohangArrayList.get(i).getSoLuong();
                        giohangArrayList.remove(i);
                        SetTongTien(v_tongtien);
                    }
                }
                UpdatePreferenceFile();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }

    public void UpdatePreferenceFile(){
        SharedPreferences.Editor v_editor = v_giohang.edit();
        String v_json = v_gson.toJson(giohangArrayList, v_type);
        v_editor.putString("giohang", v_json);
        v_editor.apply();
    }

}