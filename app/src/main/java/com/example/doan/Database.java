package com.example.doan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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

    public String LayThoiGianHienTai(){
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = df.format(calendar.getTime());
        return date;
    }

    public String LayMaNguoiDung(String v_tendangnhap){
        Cursor v_cursor = Xem("SELECT MaNguoiDung FROM NguoiDung WHERE TenDangNhap='"+v_tendangnhap+"'");
        int v_manguoidung = 0;
        while(v_cursor.moveToNext()){
            v_manguoidung = v_cursor.getInt(0);
        }
        return v_manguoidung+"";
    }

    public Sach LayMotCuonSach(String v_masach, int v_soluong){
        Cursor v_sach = Xem("SELECT * FROM Sach WHERE MaSach='"+v_masach+"'");
        v_sach.moveToNext();
        Sach a = new Sach(v_sach.getInt(0),
                v_sach.getString(1),
                v_sach.getInt(2),
                v_sach.getString(3),
                v_sach.getDouble(4)
        );
        return a;
    }

    public ArrayList<Sach> LayDanhSachSach(){
        ArrayList<Sach> a = new ArrayList<>();
        Cursor v_danhsach = Xem("SELECT * FROM Sach");
        while(v_danhsach.moveToNext()){
            //Toast.makeText(this, ten, Toast.LENGTH_LONG).show();
            a.add(new Sach(
                    v_danhsach.getInt(0),
                    v_danhsach.getString(1),
                    v_danhsach.getInt(2),
                    v_danhsach.getString(3),
                    v_danhsach.getDouble(4)
            ));
        }
        return a;
    }

    public ArrayList<Sach> TimKiemSach(String v_searchValue){
        ArrayList<Sach> a = new ArrayList<>();
        Cursor v_danhsach = Xem("SELECT * FROM Sach WHERE tensach LIKE '%"+v_searchValue+"%'");
        while(v_danhsach.moveToNext()){
            a.add(new Sach(
                    v_danhsach.getInt(0),
                    v_danhsach.getString(1),
                    v_danhsach.getInt(2),
                    v_danhsach.getString(3),
                    v_danhsach.getDouble(4)
            ));
        }
        return a;
    }

    public ArrayList<BinhLuan> LayDanhSachBinhLuan(int v_masach){
        ArrayList<BinhLuan> a = new ArrayList<>();
        Cursor v_danhsach = Xem("SELECT TenNguoiDung, NoiDung " +
                "FROM BinhLuan b, NguoiDung n " +
                "WHERE b.manguoidung = n.manguoidung and masach='"+v_masach+"'");
        while(v_danhsach.moveToNext()){
            a.add(new BinhLuan(
                    v_danhsach.getString(0),
                    v_danhsach.getString(1)
            ));
        }
        return a;
    }

    public void ThemBinhLuan(String v_tendangnhap, int v_masach, String v_noidung){

        ThemXoaSua("INSERT INTO BinhLuan VALUES('"+LayMaNguoiDung(v_tendangnhap)+"','"+v_masach+"','"+LayThoiGianHienTai()+"','"+v_noidung+"')");
    }

    public ArrayList<String> LayTheLoaiSach(int v_masach){
        ArrayList<String> a = new ArrayList<>();
        Cursor v_danhsach = Xem("SELECT tentheloai " +
                "FROM TheLoai tl, TheLoaiSach tls " +
                "WHERE tl.matheloai = tls.matheloai AND masach='"+v_masach+"'");
        while(v_danhsach.moveToNext()){
            a.add(v_danhsach.getString(0));
        }
        return a;
    }

    public ArrayList<String> LayTacGiaSach(int v_masach){
        ArrayList<String> a = new ArrayList<>();
        Cursor v_danhsach = Xem("SELECT tentacgia " +
                "FROM TacGiaSach tgs, TacGia tg " +
                "WHERE tgs.matacgia = tg.matacgia AND masach='"+v_masach+"'");
        while(v_danhsach.moveToNext()){
            a.add(v_danhsach.getString(0));
        }
        return a;
    }

    public boolean KiemTraDangNhap(String v_tendangnhap, String v_matkhau){
        Cursor v_user = Xem("SELECT * FROM NguoiDung WHERE tendangnhap='"+v_tendangnhap.toLowerCase()+"' AND matkhau='"+v_matkhau+"'");
        if(v_user.moveToNext()){
            return true;
        }else{
            return false;
        }
    }

    public Boolean KiemTraTonTaiTaiKhoan(String v_tendangnhap){
        Cursor v_user = Xem("SELECT * FROM NguoiDung WHERE tendangnhap='"+v_tendangnhap.toLowerCase()+"'");
        if(v_user.moveToNext()){
            return true;
        }else{
            return false;
        }
    }

    public void DangKyTaiKhoan(ArrayList<String> a){
        ThemXoaSua("INSERT INTO NguoiDung VALUES(NULL,'"+a.get(0)+"','"+a.get(1).toLowerCase()+"','"+a.get(2)+"','"+a.get(3)+"','"+a.get(4)+"',0)");
    }

    public ArrayList<String> ThongTinCaNhan(String v_tendangnhap){
        ArrayList<String> a = new ArrayList<>();
        Cursor v_user = Xem("SELECT * FROM NguoiDung WHERE tendangnhap='"+v_tendangnhap.toLowerCase()+"'");
        while(v_user.moveToNext()){
            a.add(v_user.getString(0));
            a.add(v_user.getString(1));
            a.add(v_user.getString(2));
            a.add(v_user.getString(4));
            a.add(v_user.getString(5));
        }
        return a;
    }

    public void SuaTen(String v_id, String v_noidung){
        ThemXoaSua("UPDATE NguoiDung set TenNguoiDung='"+v_noidung+"' WHERE MaNguoiDung='"+v_id+"'");
    }

    public void SuaDiaChi(String v_id, String v_noidung){
        ThemXoaSua("UPDATE NguoiDung set DiaChi='"+v_noidung+"' WHERE MaNguoiDung='"+v_id+"'");
    }

    public void SuaSDT(String v_id, String v_noidung){
        ThemXoaSua("UPDATE NguoiDung set SDT='"+v_noidung+"' WHERE MaNguoiDung='"+v_id+"'");
    }

    public Boolean KiemTraMatKhauCu(String v_tendangnhap, String v_matkhau){
        Cursor v_check = Xem("SELECT * FROM NguoiDung WHERE MatKhau='"+v_matkhau+"' AND TenDangNhap='"+v_tendangnhap.toLowerCase()+"'");
        if(v_check.moveToNext()){
            return true;
        }else{
            return false;
        }
    }

    public void DoiMatKhau(String v_tendangnhap, String v_matkhaumoi){
        ThemXoaSua("UPDATE NguoiDung SET MatKhau='"+v_matkhaumoi+"' WHERE TenDangNhap='"+v_tendangnhap.toLowerCase()+"'");
    }

    public ArrayList<HoaDon> LayDanhSachHoaDon(String v_tendangnhap){
        ArrayList<HoaDon> a = new ArrayList<>();
        Cursor v_hoadon = Xem("SELECT * FROM HoaDon WHERE MaNguoiDung='"+LayMaNguoiDung(v_tendangnhap)+"'");
        while(v_hoadon.moveToNext()){
            a.add(new HoaDon(
                    v_hoadon.getInt(0),
                    v_hoadon.getString(2).substring(0,10),
                    v_hoadon.getDouble(3)
            ));
        }
        return a;
    }

    public ArrayList<CTHD> LayDanhSachCTHD(String v_mahd){
        ArrayList<CTHD> a = new ArrayList<>();
        Cursor v_cthd = Xem("SELECT Sach.MaSach, TenSach, SoLuong, DonGia " +
                "FROM CTHD, Sach " +
                "WHERE CTHD.MaSach = Sach.MaSach AND MaHD='"+v_mahd+"'");
        while(v_cthd.moveToNext()){
            a.add(new CTHD(
                    v_cthd.getInt(0),
                    v_cthd.getString(1),
                    v_cthd.getInt(2),
                    v_cthd.getDouble(3)
                    ));
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
                "NgayBinhLuan datetime not null,\n" +
                "NoiDung text,\n" +
                "primary key(MaNguoiDung, MaSach, NgayBinhLuan),\n" +
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
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Truyện Kiều', "+R.drawable.truyen_kieu+" , 'Tác phẩm kể lại cuộc đời, những thử thách và đau khổ của Thúy Kiều, một phụ nữ trẻ xinh đẹp và tài năng, phải hy sinh thân mình để cứu gia đình. Để cứu cha và em trai khỏi tù, cô bán mình kết hôn với một người đàn ông trung niên, không biết rằng anh ta là một kẻ buôn người, và bị ép làm kĩ nữ trong lầu xanh.', 500000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Đánh Thức Con Người Phi Thường Trong Bạn', "+R.drawable.danh_thuc_con_nguoi_phi_thuong_trong_ban+" , 'Đánh thức con người phi thường trong bạn” là cuốn sách giúp người đọc khám phá giá trị tiềm ẩn của bản thân để tạo nên những kết quả chính mình không ngờ đến. Cuốn sách được viết bởi Athony Robbins – một nhân chứng sống, một ngưỡi đã tìm được sự phi thường trong chính con người mình.', 30000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Conan tập 96', "+R.drawable.conan_tap_96+" , 'Kaito Kid muốn giành lấy “Đôi Môi Tiên Nữ” và lần đầu đối mặt với Heiji Hattori!\n" +
                "Makoto Kyogoku bị cuốn vào những vụ án xảy ra tại một địa điểm quay phim truyền hình…!?\n" +
                "Những thông tin mới nhất liên quan tới ông trùm của Tổ chức Áo Đen sẽ được tiết lộ!!', 20000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Nghĩ giàu làm giàu', "+R.drawable.nghi_giau_lam_giau+" , 'Think and Grow Rich - Nghĩ giàu và làm giàu là một trong những cuốn sách bán chạy nhất mọi thời đại. Đã hơn 60 triệu bản được phát hành với gần trăm ngôn ngữ trên toàn thế giới và được công nhận là cuốn sách tạo ra nhiều triệu phú, một cuốn sách truyền cảm hứng thành công nhiều hơn bất cứ cuốn sách kinh doanh nào trong lịch sử.', 40000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Doraemon tập 1', "+R.drawable.doraemon_tap_1+" , 'Nội dung series kể về cuộc đời bất hạnh của cậu bé Nobita và chú mèo robot Doraemon từ tương lai đến giúp cuộc sống cậu trở nên tốt hơn.', 10000 );");

        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Self-help');");
        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Truyện tranh');");
        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Tiểu thuyết');");

        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (1,2);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (1,4);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (3,1);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,3);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,5);");

        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Nguyễn Du');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Anthony Robbins');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Gosho Aoyama');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Napoleon Hill');");
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

        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NgayBinhLuan,NoiDung) VALUES (1,4,'2020-04-10 07:27:35','Hay quá.');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NgayBinhLuan,NoiDung) VALUES (2,5,'2020-04-10 07:27:35','Hay ghê.');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NgayBinhLuan,NoiDung) VALUES (3,3,'2020-04-10 07:27:35','Mai mua thêm cuốn.');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NgayBinhLuan,NoiDung) VALUES (4,2,'2020-04-10 07:27:35','Tác giả chém gió hay thật');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NgayBinhLuan,NoiDung) VALUES (5,1,'2020-04-10 07:27:35','Hay quá xá.');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NgayBinhLuan,NoiDung) VALUES (6,4,'2020-04-10 07:27:35','Vừa tào lao mà cũng vừa có lý :v');");

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

    /*@Override
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
    }*/
}
