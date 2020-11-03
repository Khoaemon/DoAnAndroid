package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView sachGridView;
    ArrayList<Sach> sachArrayList;
    SachAdapter v_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemhang);

        AnhXa();

        sachArrayList.add(new Sach(1,"Học lập trình Android từ cơ bản đến nâng cao",null,"Dành cho những người mới bắt đầu", 150000));
        sachArrayList.add(new Sach(2,"Học lập trình Android từ cơ bản đến nâng  cao 2",null,"Dành cho những người mới bắt đầu", 200000));
        sachArrayList.add(new Sach(3,"Học lập trình Android từ cơ bản đến nâng  cao 3",null,"Dành cho những người mới bắt đầu", 250000));

        sachGridView.setAdapter(v_adapter);

    }

    private void AnhXa() {
        sachGridView = (GridView) findViewById(R.id.gridviewBook);
        sachArrayList = new ArrayList<>();
        v_adapter = new SachAdapter(MainActivity.this, R.layout.o_sach, sachArrayList);
    }
}