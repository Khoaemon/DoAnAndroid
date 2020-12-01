package com.example.doan.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.example.doan.Database;
import com.example.doan.MainActivity;
import com.example.doan.R;
import com.example.doan.Sach;
import com.example.doan.SachAdapter;

import java.util.ArrayList;

public class SachFragment extends Fragment {

    GridView sachGridView;
    Button btnTimKiem;
    EditText edtTimKiem;
    SachAdapter v_adapter;
    Database v_dtb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sach, container, false);

        AnhXa(view);

        /*sachArrayList.add(new Sach(1,"Học lập trình Android từ cơ bản đến nâng cao","","Dành cho những người mới bắt đầu", 150000,0));
        sachArrayList.add(new Sach(2,"Học lập trình Android từ cơ bản đến nâng  cao 2","","Dành cho những người mới bắt đầu", 200000,0));
        sachArrayList.add(new Sach(3,"Học lập trình Android từ cơ bản đến nâng  cao 3","","Dành cho những người mới bắt đầu", 250000,0));
        sachArrayList.add(new Sach(1,"Học lập trình Android từ cơ bản đến nâng cao","","Dành cho những người mới bắt đầu", 150000,0));
        sachArrayList.add(new Sach(2,"Học lập trình Android từ cơ bản đến nâng  cao 2","","Dành cho những người mới bắt đầu", 200000,0));
        sachArrayList.add(new Sach(3,"Học lập trình Android từ cơ bản đến nâng  cao 3","","Dành cho những người mới bắt đầu", 250000,0));*/

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v_adapter = new SachAdapter(getContext(), R.layout.o_sach, v_dtb.TimKiemSach(edtTimKiem.getText().toString()));
                v_adapter.notifyDataSetChanged();
                sachGridView.setAdapter(v_adapter);
            }
        });



        v_adapter = new SachAdapter(getContext(), R.layout.o_sach, v_dtb.LayDanhSachSach());
        v_adapter.notifyDataSetChanged();
        sachGridView.setAdapter(v_adapter);

        return view;
    }

    private void AnhXa(View view) {
        btnTimKiem = (Button) view.findViewById(R.id.buttonSearch);
        edtTimKiem = (EditText) view.findViewById(R.id.editTextSearch);
        sachGridView = (GridView) view.findViewById(R.id.gridviewBook);
        v_dtb = new Database(getContext());
    }
}