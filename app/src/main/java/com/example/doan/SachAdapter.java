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

public class SachAdapter extends BaseAdapter {

    private Context v_context;
    private int c_layout;
    private ArrayList<Sach> sachArrayList;

    public SachAdapter(Context v_context, int c_layout, ArrayList<Sach> sachArrayList) {
        this.v_context = v_context;
        this.c_layout = c_layout;
        this.sachArrayList = sachArrayList;
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

        TextView v_tensach = (TextView) convertView.findViewById(R.id.textViewTenSach);
        TextView v_giaban = (TextView) convertView.findViewById(R.id.textViewGiaBan);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgSach);
        Button btnThem = (Button) convertView.findViewById(R.id.buttonThem);

        Sach v_sach = sachArrayList.get(position);
        v_tensach.setText(v_sach.getTenSach());
        v_giaban.setText(String.valueOf(v_sach.getGia())+" đồng");

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v_context, "Đã thêm vào giỏ hàng.", Toast.LENGTH_SHORT).show();
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v_context, "OK!", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
