package com.example.doan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class BinhLuanAdapter extends BaseAdapter {

    private Context v_context;
    private int c_layout;
    private ArrayList<BinhLuan> binhluanArrayList;

    public BinhLuanAdapter(Context v_context, int c_layout, ArrayList<BinhLuan> binhluanArrayList) {
        this.v_context = v_context;
        this.c_layout = c_layout;
        this.binhluanArrayList = binhluanArrayList;
    }

    @Override
    public int getCount() {
        return binhluanArrayList.size();
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

        TextView v_ten = (TextView) convertView.findViewById(R.id.textViewTenNguoiBinhLuan);
        TextView v_noidung = (TextView) convertView.findViewById(R.id.textViewNoiDungBinhLuan);

        BinhLuan v_binhluan = binhluanArrayList.get(position);
        v_ten.setText(v_binhluan.getTenNguoiDung());
        v_noidung.setText(v_binhluan.getNoiDung());

        return convertView;
    }
}
