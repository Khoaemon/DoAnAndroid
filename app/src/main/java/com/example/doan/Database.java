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
        Cursor v_danhsach = Xem("SELECT * FROM Sach");
        while(v_danhsach.moveToNext()){
            //Toast.makeText(this, ten, Toast.LENGTH_LONG).show();
            a.add(new Sach(v_danhsach.getInt(0), v_danhsach.getString(1), v_danhsach.getInt(2), v_danhsach.getString(3), v_danhsach.getDouble(4)));
        }
        return a;
    }

    public ArrayList<BinhLuan> LayDanhSachBinhLuan(int masach){
        ArrayList<BinhLuan> a = new ArrayList<>();
        Cursor v_danhsach = Xem("SELECT TenNguoiDung, NoiDung " +
                "FROM BinhLuan b, NguoiDung n " +
                "WHERE b.manguoidung = n.manguoidung and masach='"+masach+"'");
        while(v_danhsach.moveToNext()){
            //Toast.makeText(this, ten, Toast.LENGTH_LONG).show();
            a.add(new BinhLuan(v_danhsach.getString(0), v_danhsach.getString(1)));
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
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Truyện Kiều', "+R.drawable.truyen_kieu+" , 'Sách của Nguyễn DU', 500000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Đánh thức con người phi thường trong bạn', "+R.drawable.danh_thuc_con_nguoi_phi_thuong_trong_ban+" , 'Sách về động lực thúc đẩy bản thân', 30000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Conan tập 96', "+R.drawable.conan_tap_96+" , 'Bộ truyện tranh thám tử hay nhất của Nhật bản', 20000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Nghĩ giàu làm giàu', "+R.drawable.nghi_giau_lam_giau+" , 'sách thay đổi tư duy về việc kiếm tiền', 40000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Doraemon tập 1', "+R.drawable.doraemon_tap_1+" , 'Chú mèo máy đến từ tương lai', 10000 );");

        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Self-help');");
        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Truyen tranh');");
        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Tiểu thuyết');");

        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (1,2);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (1,4);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (3,1);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,3);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,5);");

        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Nguyễn Du');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Anthony Robbins');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Napoleon Hill');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Gosho Aoyama');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Fujiko F. Fujio');");

        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (1,1);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (2,2);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (3,3);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (4,4);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (5,5);");

        db.execSQL("INSERT INTO NguoiDung (TenNguoiDung,TenDangNhap,MatKhau,DiaChi,SDT,LoaiNguoiDung) VALUES ('Đỗ Minh Nhựt','nhut','matkhau1','KTX B',01234567,1);");
        db.execSQL("INSERT INTO NguoiDung (TenNguoiDung,TenDangNhap,MatKhau,DiaChi,SDT,LoaiNguoiDung) VALUES ('Anh Khoa','khoa','matkhau2','KTX B',01234789,1);");
        db.execSQL("INSERT INTO NguoiDung (TenNguoiDung,TenDangNhap,MatKhau,DiaChi,SDT,LoaiNguoiDung) VALUES ('Công Hoàng','hoang','matkhau3','NHA TRO',01234456,1);");
        db.execSQL("INSERT INTO NguoiDung (TenNguoiDung,TenDangNhap,MatKhau,DiaChi,SDT,LoaiNguoiDung) VALUES ('Ronaldo','nguongdung1','matkhau4','QUAN 1',01234123,0);");
        db.execSQL("INSERT INTO NguoiDung (TenNguoiDung,TenDangNhap,MatKhau,DiaChi,SDT,LoaiNguoiDung) VALUES ('Messi','nguoidung2','matkhau5','QUAN 2',02354567,0);");
        db.execSQL("INSERT INTO NguoiDung (TenNguoiDung,TenDangNhap,MatKhau,DiaChi,SDT,LoaiNguoiDung) VALUES ('Ronaldinho','nguoidung3','matkhau6','QUAN 3',05734567,0);");

        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NoiDung) VALUES (1,4,'Truyện hay quá.');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NoiDung) VALUES (2,5,'Truyện hay ghê.');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NoiDung) VALUES (3,3,'Mai mua thêm cuốn.');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NoiDung) VALUES (4,2,'Truyện này tác giả chém gió hay thật');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NoiDung) VALUES (5,1,'Truyện tranh sao giống kinh dị vậy??');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NoiDung) VALUES (6,4,'Truyện vừa tào lao mà cũng vừa có lý :v');");

        db.execSQL("INSERT INTO HoaDon (MaNguoiDung,NgayXuat,ThanhTien) VALUES (1,'2020-04-10 07:27:35', 300000.00);");
        db.execSQL("INSERT INTO HoaDon (MaNguoiDung,NgayXuat,ThanhTien) VALUES (2,'2020-05-20 08:27:09', 400000.00);");
        db.execSQL("INSERT INTO HoaDon (MaNguoiDung,NgayXuat,ThanhTien) VALUES (3,'2020-06-25 15:27:19', 500000.00);");
        db.execSQL("INSERT INTO HoaDon (MaNguoiDung,NgayXuat,ThanhTien) VALUES (4,'2020-07-14 17:27:29', 600000.00);");
        db.execSQL("INSERT INTO HoaDon (MaNguoiDung,NgayXuat,ThanhTien) VALUES (5,'2020-08-23 20:27:30', 700000.00);");
        db.execSQL("INSERT INTO HoaDon (MaNguoiDung,NgayXuat,ThanhTien) VALUES (6,'2020-09-09 08:27:40', 800000.00);");

        db.execSQL("INSERT INTO CTHD (MaHD,MaSach,SoLuong,DonGia) VALUES (1,3,2,600000.00);");
        db.execSQL("INSERT INTO CTHD (MaHD,MaSach,SoLuong,DonGia) VALUES (3,1,1,500000.00);");
        db.execSQL("INSERT INTO CTHD (MaHD,MaSach,SoLuong,DonGia) VALUES (2,2,1,400000.00);");
        db.execSQL("INSERT INTO CTHD (MaHD,MaSach,SoLuong,DonGia) VALUES (4,5,2,1200000.00);");
        db.execSQL("INSERT INTO CTHD (MaHD,MaSach,SoLuong,DonGia) VALUES (6,4,1,800000.00);");
        db.execSQL("INSERT INTO CTHD (MaHD,MaSach,SoLuong,DonGia) VALUES (5,5,1,700000.00);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL("drop table if exists BINHLUAN;");
        db.execSQL("drop table if exists CTHD;\n");
        db.execSQL("drop table if exists HOADON;");
        db.execSQL("drop table if exists NGUOIDUNG;");
        db.execSQL("drop table if exists TACGIASACH;\n");
        db.execSQL("drop table if exists TACGIA;");
        db.execSQL("drop table if exists THELOAI;");
        db.execSQL("drop table if exists THELOAISACH;");
        db.execSQL("drop table if exists SACH;");
        onCreate(db);
    }
}
