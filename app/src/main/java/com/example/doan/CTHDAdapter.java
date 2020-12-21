package com.example.doan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CTHDAdapter extends BaseAdapter {

    private Context v_context;
    private int c_layout;
    private ArrayList<CTHD> cthdArrayList;

    public CTHDAdapter(Context v_context, int c_layout, ArrayList<CTHD> cthdArrayList) {
        this.v_context = v_context;
        this.c_layout = c_layout;
        this.cthdArrayList = cthdArrayList;
    }

    @Override
    public int getCount() {
        return cthdArrayList.size();
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

        TextView masach  = convertView.findViewById(R.id.textViewCTHDMaSach);
        TextView tensach  = convertView.findViewById(R.id.textViewCTHDTenSach);
        TextView soluong  = convertView.findViewById(R.id.textViewCTHDSoLuong);
        TextView dongia  = convertView.findViewById(R.id.textViewCTHDDonGia);


        CTHD v_cthd = cthdArrayList.get(position);

        masach.setText(v_cthd.getMaSach()+"");
        tensach.setText(v_cthd.getTenSach());
        soluong.setText(v_cthd.getSoLuong()+"");
        dongia.setText(NumberFormat.getNumberInstance(Locale.US).format(v_cthd.getDonGia())+"");

        return convertView;
    }
}
