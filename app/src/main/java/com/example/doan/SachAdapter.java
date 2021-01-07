package com.example.doan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class SachAdapter extends BaseAdapter {

    private Context v_context;
    private int c_layout;
    private ArrayList<Sach> sachArrayList;
    private SharedPreferences v_giohang;
    private ArrayList<GioHang> v_gioHangArrayList;

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

    private class ViewHolder{
        TextView v_tensach;
        TextView v_giaban;
        ImageView img;
        Button btnThem;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null){
            LayoutInflater v_inflater = (LayoutInflater) v_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = v_inflater.inflate(c_layout, null);

            holder = new ViewHolder();

            holder.v_tensach = (TextView) convertView.findViewById(R.id.textViewTenSach);
            holder.v_giaban = (TextView) convertView.findViewById(R.id.textViewGiaBan);
            holder.img = (ImageView) convertView.findViewById(R.id.imgSach);
            holder.btnThem = (Button) convertView.findViewById(R.id.buttonThem);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }


        v_giohang = v_context.getSharedPreferences("giohang", MODE_PRIVATE);
        v_gioHangArrayList = new ArrayList<>();

        final Sach v_sach = sachArrayList.get(position);
        holder.v_tensach.setText(v_sach.getTenSach());
        holder.v_giaban.setText(NumberFormat.getNumberInstance(Locale.US).format(v_sach.getGia())+" VNĐ");
        holder.img.setImageResource(v_sach.getImgURL());

        holder.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Type v_type = new TypeToken<ArrayList<GioHang>>(){}.getType();
                Gson v_gson = new Gson();
                String v_json1 = v_giohang.getString("giohang","");
                v_gioHangArrayList = v_gson.fromJson(v_json1, v_type);
                Boolean flag = false;

                if (v_gioHangArrayList==null) {
                    v_gioHangArrayList = new ArrayList<>();
                    v_gioHangArrayList.add(new GioHang(v_sach.getMaSach(), 1));
                }else{
                    for(int i = 0; i < v_gioHangArrayList.size(); i++){
                        if(v_gioHangArrayList.get(i).getMaSach()==v_sach.getMaSach()){
                            int v_soluong = v_gioHangArrayList.get(i).getSoLuong();
                            v_gioHangArrayList.get(i).setSoLuong(++v_soluong);
                            flag = true;
                        }
                    }
                    if(flag==false){
                        v_gioHangArrayList.add(new GioHang(v_sach.getMaSach(), 1));
                    }
                }

                SharedPreferences.Editor v_editor = v_giohang.edit();
                String v_json2 = v_gson.toJson(v_gioHangArrayList, v_type);
                v_editor.putString("giohang", v_json2);
                v_editor.apply();
            }
        });

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v_context, ChitietsachActivity.class);
                intent.putExtra("sach", v_sach);
                v_context.startActivity(intent);
            }
        });

        Animation v_animation = AnimationUtils.loadAnimation(v_context, R.anim.list_animation);
        convertView.startAnimation(v_animation);

        return convertView;
    }
}
