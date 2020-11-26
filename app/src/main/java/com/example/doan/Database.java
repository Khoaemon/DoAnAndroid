package com.example.doan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {



    public Database(Context context) {
        super(context, "bansach.sqlite", null, 1);
    }

    public void ThemXoaSua(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor Xem(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public ArrayList<Sach> LayDanhSachSach(){
        ArrayList<Sach> a = new ArrayList<>();
        Cursor danhsach = Xem("SELECT * FROM Sach");
        while(danhsach.moveToNext()){
            //Toast.makeText(this, ten, Toast.LENGTH_LONG).show();
            a.add(new Sach(danhsach.getInt(0), danhsach.getString(1), danhsach.getInt(2), danhsach.getString(3), danhsach.getDouble(4)));
        }

        return a;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  Sach(\n" +
                "\tMaSach INTEGER primary key AUTOINCREMENT,\n" +
                "\tTenSach varchar(100),\n" +
                "\tImgURL int,\n" +
                "\tMoTa text,\n" +
                "\tGia decimal(8,2)\n" +
                ");");
        db.execSQL("create table  TheLoaiSach(\n" +
                "MaTheLoai INTEGER primary key AUTOINCREMENT,\n" +
                "TenTheLoai varchar(255)\n" +
                ");");
        db.execSQL("CREATE TABLE  TheLoai(\n" +
                "MaTheLoai INTEGER not null,\n" +
                "MaSach INTEGER not null,\n" +
                "primary key(MaTheLoai, MaSach),\n" +
                "FOREIGN key(MaSach) REFERENCES Sach(MaSach),\n" +
                "FOREIGN key(MaTheLoai) REFERENCES TheLoaiSach(MaTheLoai)\n" +
                ");");
        db.execSQL("CREATE TABLE  TacGia(\n" +
                "MaTacGia INTEGER primary key AUTOINCREMENT,\n" +
                "TenTacGia varchar(255)\n" +
                ");");
        db.execSQL("CREATE TABLE  TacGiaSach(\n" +
                "MaTacGia INTEGER not null,\n" +
                "MaSach INTEGER not null,\n" +
                "primary key(MaTacGia, MaSach),\n" +
                "FOREIGN key(MaSach) REFERENCES Sach(MaSach),\n" +
                "FOREIGN key(MaTacGia) REFERENCES TacGia(MaTacGia)\n" +
                ");");
        db.execSQL("CREATE TABLE  BinhLuan(\n" +
                "MaNguoiDung INTEGER not null,\n" +
                "MaSach INTEGER not null,\n" +
                "NoiDung text,\n" +
                "primary key(MaNguoiDung, MaSach),\n" +
                "FOREIGN key(MaSach) REFERENCES Sach(MaSach),\n" +
                "FOREIGN key(MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)\n" +
                ");");
        db.execSQL("Create table  NguoiDung(\n" +
                "MaNguoiDung INTEGER primary key AUTOINCREMENT,\n" +
                "TenNguoiDung varchar(100),\n" +
                "TenDangNhap varchar(100),\n" +
                "MatKhau varchar(100),\n" +
                "DiaChi varchar(100),\n" +
                "SDT varchar(10),\n" +
                "LoaiNguoiDung INTEGER\n" +
                ");");
        db.execSQL("Create table  HoaDon(\n" +
                "\tMaHD INTEGER primary key AUTOINCREMENT,\n" +
                "\tMaNguoiDung INTEGER not null,\n" +
                "\tNgayXuat datetime,\n" +
                "\tThanhTien decimal(8,2),\n" +
                "\tFOREIGN key(MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)\n" +
                ");");
        db.execSQL("create table  CTHD(\n" +
                "MaHD INTEGER not null,\n" +
                "MaSach INTEGER not null,\n" +
                "SoLuong INTEGER,\n" +
                "DonGia decimal(8,2),\n" +
                "primary key(MaHD,MaSach),\n" +
                "FOREIGN key(MaHD) REFERENCES HoaDon(MaHD),\n" +
                "FOREIGN key(MaSach) REFERENCES Sach(MaSach)\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
