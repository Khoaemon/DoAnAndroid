package com.example.doan.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.doan.Database;
import com.example.doan.MainActivity;
import com.example.doan.R;
import com.example.doan.Sach;
import com.example.doan.SachAdapter;

import java.util.ArrayList;

public class SachFragment extends Fragment {

    GridView sachGridView;
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

        v_dtb.ThemXoaSua("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Truyện Kiều', "+R.drawable.truyen_kieu+" , 'Sách của Nguyễn DU', 500000 );");
        v_dtb.ThemXoaSua("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Đánh thức con người phi thường trong bạn', "+R.drawable.danh_thuc_con_nguoi_phi_thuong_trong_ban+" , 'Sách về động lực thúc đẩy bản thân', 30000 );");
        v_dtb.ThemXoaSua("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Conan tập 96', "+R.drawable.conan_tap_96+" , 'Bộ truyện tranh thám tử hay nhất của Nhật bản', 20000 );");
        v_dtb.ThemXoaSua("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Nghĩ giàu làm giàu', "+R.drawable.nghi_giau_lam_giau+" , 'sách thay đổi tư duy về việc kiếm tiền', 40000 );");
        v_dtb.ThemXoaSua("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Doraemon tập 1', "+R.drawable.doraemon_tap_1+" , 'Chú mèo máy đến từ tương lai', 10000 );");

        v_adapter = new SachAdapter(getContext(), R.layout.o_sach, v_dtb.LayDanhSachSach());
        v_adapter.notifyDataSetChanged();
        sachGridView.setAdapter(v_adapter);

        return view;
    }

    private void AnhXa(View view) {
        sachGridView = (GridView) view.findViewById(R.id.gridviewBook);
        v_dtb = new Database(getContext());
    }
}