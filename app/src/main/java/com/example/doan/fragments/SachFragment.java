package com.example.doan.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.doan.R;
import com.example.doan.Sach;
import com.example.doan.SachAdapter;

import java.util.ArrayList;

public class SachFragment extends Fragment {

    GridView sachGridView;
    ArrayList<Sach> sachArrayList;
    SachAdapter v_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sach, container, false);

        AnhXa(view);

        sachArrayList.add(new Sach(1,"Học lập trình Android từ cơ bản đến nâng cao","","Dành cho những người mới bắt đầu", 150000,0));
        sachArrayList.add(new Sach(2,"Học lập trình Android từ cơ bản đến nâng  cao 2","","Dành cho những người mới bắt đầu", 200000,0));
        sachArrayList.add(new Sach(3,"Học lập trình Android từ cơ bản đến nâng  cao 3","","Dành cho những người mới bắt đầu", 250000,0));
        sachArrayList.add(new Sach(1,"Học lập trình Android từ cơ bản đến nâng cao","","Dành cho những người mới bắt đầu", 150000,0));
        sachArrayList.add(new Sach(2,"Học lập trình Android từ cơ bản đến nâng  cao 2","","Dành cho những người mới bắt đầu", 200000,0));
        sachArrayList.add(new Sach(3,"Học lập trình Android từ cơ bản đến nâng  cao 3","","Dành cho những người mới bắt đầu", 250000,0));

        sachGridView.setAdapter(v_adapter);

        return view;
    }

    private void AnhXa(View view) {
        sachGridView = (GridView) view.findViewById(R.id.gridviewBook);
        sachArrayList = new ArrayList<>();
        v_adapter = new SachAdapter(getContext(), R.layout.o_sach, sachArrayList);
    }
}