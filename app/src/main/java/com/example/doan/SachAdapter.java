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
    private ArrayList<GioHang> v_gioHangArrayList;
    private LinkedHashMap<Integer, Integer> v_lhm;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater v_inflater = (LayoutInflater) v_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = v_inflater.inflate(c_layout, null);

        TextView v_tensach = (TextView) convertView.findViewById(R.id.textViewTenSach);
        TextView v_giaban = (TextView) convertView.findViewById(R.id.textViewGiaBan);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgSach);
        Button btnThem = (Button) convertView.findViewById(R.id.buttonThem);
        v_giohang = v_context.getSharedPreferences("giohang", MODE_PRIVATE);
        v_gioHangArrayList = new ArrayList<>();
        v_lhm = new LinkedHashMap<Integer, Integer>();

        final Sach v_sach = sachArrayList.get(position);
        v_tensach.setText(v_sach.getTenSach());
        v_giaban.setText(v_sach.getGia()+" VNĐ");
        img.setImageResource(v_sach.getImgURL());

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (v_lhm.get(v_sach.getMaSach())==null){
                    v_lhm.put(v_sach.getMaSach(), 1);
                    v_gioHangArrayList.add(new GioHang(v_sach.getMaSach()));
                }else{
                    int v_soluong = v_lhm.get(v_sach.getMaSach());
                    v_lhm.put(v_sach.getMaSach(), v_soluong++);
                    for(int i = 0; i < v_gioHangArrayList.size(); i++){
                        if(v_gioHangArrayList.get(i).getMaSach()==v_sach.getMaSach()){

                        }
                    }
                }*/

                Gson v_gson = new Gson();
                String v_json1 = v_giohang.getString("giohang","");
                v_gioHangArrayList = v_gson.fromJson(v_json1, ArrayList.class);
                Boolean flag = false;

                if (v_gioHangArrayList==null) {
                    v_gioHangArrayList = new ArrayList<>();
                    v_gioHangArrayList.add(new GioHang(v_sach.getMaSach(), 0));
                }else{
                    for(int i = 0; i < v_gioHangArrayList.size(); i++){
                        if(v_gioHangArrayList.get(i).getMaSach()==v_sach.getMaSach()){
                            int v_soluong = v_gioHangArrayList.get(i).getSoLuong();
                            v_gioHangArrayList.get(i).setSoLuong(v_soluong++);
                            flag = true;
                        }
                    }
                    if(flag==false){
                        v_gioHangArrayList.add(new GioHang(v_sach.getMaSach(), 0));
                    }
                }

                SharedPreferences.Editor v_editor = v_giohang.edit();
                String v_json2 = v_gson.toJson(v_gioHangArrayList);
                v_editor.putString("giohang", v_json2);
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
