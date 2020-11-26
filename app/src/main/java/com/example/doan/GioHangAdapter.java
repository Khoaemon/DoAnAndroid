package com.example.doan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.EventListener;

public class GioHangAdapter extends BaseAdapter {

    private Context v_context;
    private int c_layout;
    private ArrayList<Sach> sachArrayList;
    EventListener listener;

    public interface EventListener {
        void DialogXoaGioHang(String tensach);
    }

    public GioHangAdapter(Context v_context, int c_layout, ArrayList<Sach> sachArrayList, EventListener listener) {
        this.v_context = v_context;
        this.c_layout = c_layout;
        this.sachArrayList = sachArrayList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return sachArrayList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater v_inflater = (LayoutInflater) v_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = v_inflater.inflate(c_layout, null);

        TextView v_tensach = (TextView) convertView.findViewById(R.id.textViewTenSachGioHang);
        TextView v_giaban = (TextView) convertView.findViewById(R.id.textViewGiaSachGioHang);
        TextView v_sl = (TextView) convertView.findViewById(R.id.textViewSL);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgSanPhamGioHang);
        Button btnTang = (Button) convertView.findViewById(R.id.buttonTang);
        Button btnGiam = (Button) convertView.findViewById(R.id.buttonGiam);
        Button btnHuy = (Button) convertView.findViewById(R.id.buttonHuy);

        final Sach v_sanphamgiohang = sachArrayList.get(position);

        v_tensach.setText(v_sanphamgiohang.getTenSach());
        v_giaban.setText(v_sanphamgiohang.getGia()+"");
        //v_sl.setText(v_sanphamgiohang.getSoLuong()+"");

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.DialogXoaGioHang(v_sanphamgiohang.getTenSach());
            }
        });

        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v_context, "Tăng", Toast.LENGTH_SHORT).show();
            }
        });

        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v_context, "Giảm", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }


}
