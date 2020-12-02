package com.example.doan;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.EventListener;

public class GioHangAdapter extends BaseAdapter {

    private Context v_context;
    private int c_layout;
    private ArrayList<GioHang> gioHangArrayList;
    private EventListener listener;
    private SharedPreferences v_giohang;
    private Gson v_gson;
    private Type v_type;


    public interface EventListener {
        void DialogXoaGioHang(int v_masach, String v_tensach);
        double GetTongTien();
        void SetTongTien(double a);
    }

    public GioHangAdapter(Context v_context, int c_layout, ArrayList<GioHang> gioHangArrayList, EventListener listener) {
        this.v_context = v_context;
        this.c_layout = c_layout;
        this.gioHangArrayList = gioHangArrayList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return gioHangArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater v_inflater = (LayoutInflater) v_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = v_inflater.inflate(c_layout, null);

        TextView v_tensach = (TextView) convertView.findViewById(R.id.textViewTenSachGioHang);
        final TextView v_giaban = (TextView) convertView.findViewById(R.id.textViewGiaSachGioHang);
        final TextView v_sl = (TextView) convertView.findViewById(R.id.textViewSL);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgSanPhamGioHang);
        ImageView btnTang = (ImageView) convertView.findViewById(R.id.buttonTang);
        ImageView btnGiam = (ImageView) convertView.findViewById(R.id.buttonGiam);
        ImageView btnHuy = (ImageView) convertView.findViewById(R.id.buttonHuy);
        v_giohang = v_context.getSharedPreferences("giohang", Context.MODE_PRIVATE);
        v_type = new TypeToken<ArrayList<GioHang>>(){}.getType();
        v_gson = new Gson();


        final GioHang v_sanphamgiohang = gioHangArrayList.get(position);

        v_tensach.setText(v_sanphamgiohang.getTenSach());
        v_giaban.setText(v_sanphamgiohang.getGia()+" VNĐ");
        v_sl.setText(v_sanphamgiohang.getSoLuong()+"");
        img.setImageResource(v_sanphamgiohang.getImg());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.DialogXoaGioHang(v_sanphamgiohang.getMaSach(), v_sanphamgiohang.getTenSach());
            }
        });

        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int v_soluonghientai =  v_sanphamgiohang.getSoLuong();
                v_soluonghientai++;
                v_sl.setText(v_soluonghientai+"");
                v_sanphamgiohang.setSoLuong(v_soluonghientai);
                listener.SetTongTien(listener.GetTongTien() + v_sanphamgiohang.getGia());
                UpdatePreferenceFile();
            }
        });

        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int v_soluonghientai =  v_sanphamgiohang.getSoLuong();
                v_soluonghientai--;
                if(v_soluonghientai == 0){
                    gioHangArrayList.remove(position);
                }else{
                    v_sl.setText(v_soluonghientai+"");
                    v_sanphamgiohang.setSoLuong(v_soluonghientai);
                }
                listener.SetTongTien(listener.GetTongTien() - v_sanphamgiohang.getGia());
                UpdatePreferenceFile();
            }
        });

        return convertView;
    }

    public void UpdatePreferenceFile(){
        SharedPreferences.Editor v_editor = v_giohang.edit();
        String v_json = v_gson.toJson(gioHangArrayList, v_type);
        v_editor.putString("giohang", v_json);
        v_editor.apply();
    }


}
