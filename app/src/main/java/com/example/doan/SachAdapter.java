package com.example.doan;

import android.content.Context;
import android.content.Intent;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static android.content.Context.MODE_PRIVATE;

public class SachAdapter extends BaseAdapter {

    private Context v_context;
    private int c_layout;
    private ArrayList<Sach> sachArrayList;
    private SharedPreferences v_giohang;
    LinkedHashMap<Integer, Integer> v_lhm;

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
        v_giohang = v_context.getSharedPreferences("giohang", MODE_PRIVATE);
        v_lhm = new LinkedHashMap<>();

        final Sach v_sach = sachArrayList.get(position);
        v_tensach.setText(v_sach.getTenSach());
        v_giaban.setText(v_sach.getGia()+" VNĐ");
        img.setImageResource(v_sach.getImgURL());

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v_lhm.get(v_sach.getMaSach())==null){
                    v_lhm.put(v_sach.getMaSach(), 1);
                }else{
                    int v_soluong = v_lhm.get(v_sach.getMaSach());
                    v_lhm.put(v_sach.getMaSach(), v_soluong++);
                }
                SharedPreferences.Editor v_editor = v_giohang.edit();
                Gson v_gson = new Gson();
                String v_json = v_gson.toJson(v_lhm);
                v_editor.putString("giohang", v_json);
                v_editor.commit();
                Toast.makeText(v_context, "Đã thêm vào giỏ hàng.", Toast.LENGTH_SHORT).show();
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v_context, ChitietsachActivity.class);
                intent.putExtra("sach", v_sach);
                v_context.startActivity(intent);
            }
        });

        return convertView;
    }
}
