package com.example.doan;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HoaDonAdapter extends BaseAdapter {

    private Context v_context;
    private int c_layout;
    private ArrayList<HoaDon> hdArrayList;

    public HoaDonAdapter(Context v_context, int c_layout, ArrayList<HoaDon> hdArrayList) {
        this.v_context = v_context;
        this.c_layout = c_layout;
        this.hdArrayList = hdArrayList;
    }

    @Override
    public int getCount() {
        return hdArrayList.size();
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

        TextView ngayxuatHD = convertView.findViewById(R.id.textViewNgayXuatHD);
        TextView maHD = convertView.findViewById(R.id.textViewMaHD);
        TextView thanhtien = convertView.findViewById(R.id.textViewTienHD);

        HoaDon v_hd = hdArrayList.get(position);

        ngayxuatHD.setText(v_hd.getNgayXuat());
        maHD.setText(v_hd.getMaHD()+"");
        if(v_hd.getTrangThai()==0){
            thanhtien.setText("ĐÃ HỦY");
            thanhtien.setTextColor(Color.parseColor("#FF0000"));
        }else{
            thanhtien.setText(NumberFormat.getNumberInstance(Locale.US).format(v_hd.getThanhTien())+"");
        }


        return convertView;
    }
}
