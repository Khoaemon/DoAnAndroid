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

    public Sach LayMotCuonSach(int v_masach){
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
        Cursor v_danhsach = Xem("SELECT TenNguoiDung, NoiDung, NgayBinhLuan " +
                "FROM BinhLuan b, NguoiDung n " +
                "WHERE b.manguoidung = n.manguoidung and masach='"+v_masach+"'");
        while(v_danhsach.moveToNext()){
            a.add(new BinhLuan(
                    v_danhsach.getString(0),
                    v_danhsach.getString(1),
                    v_danhsach.getString(2)
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
                    v_hoadon.getString(3).substring(0,10),
                    v_hoadon.getDouble(4),
                    v_hoadon.getInt(5)
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

    public ArrayList<String> LayDanhSachTTGH(String v_mahd){
        ArrayList<String> a = new ArrayList<>();
        Cursor v_ttgh = Xem("SELECT HoTen, SDT, DiaChi, GhiChu, TrangThai " +
                "FROM HoaDon HD, ThongTinGiaoHang TTGH " +
                "WHERE HD.MaTTGH = TTGH.MaTTGH AND MaHD='"+v_mahd+"'");
        while(v_ttgh.moveToNext()){
            a.add(v_ttgh.getString(0));
            a.add(v_ttgh.getString(1));
            a.add(v_ttgh.getString(2));
            a.add(v_ttgh.getString(3));
            switch (v_ttgh.getInt(4)){
                case 0: a.add("ĐÃ HỦY"); break;
                case 1: a.add("ĐANG CHỜ TIẾP NHẬN"); break;
                case 2: a.add("ĐANG VẬN CHUYỂN"); break;
                case 3: a.add("ĐÃ GIAO THÀNH CÔNG!"); break;
            }

        }
        return a;
    }

    public int ThemTTGH(ArrayList<String> a){
        ThemXoaSua("INSERT INTO ThongTinGiaoHang values(null, '"+a.get(0)+"', '"+a.get(1)+"', '"+a.get(2)+"', '"+a.get(3)+"')");
        Cursor v_cursor = Xem("SELECT last_insert_rowid()");
        int v_mattgh = 0;
        while(v_cursor.moveToNext()){
            v_mattgh = v_cursor.getInt(0);
        }
        return v_mattgh;
    }

    public void ThemHoaDon(String v_tendangnhap, ArrayList<GioHang> a, double v_tongtien, int v_mattgh){
        String v_manguoidung = "";
        if(v_tendangnhap.equals("")){
            ThemXoaSua("INSERT INTO NGUOIDUNG VALUES(null, 'Vãng Lai', null, null, null, null, 0)");
            Cursor v_cursor1 = Xem("SELECT last_insert_rowid()");
            while(v_cursor1.moveToNext()){
                v_manguoidung = v_cursor1.getInt(0)+"";
            }
        }else{
            v_manguoidung = LayMaNguoiDung(v_tendangnhap);
        }
        ThemXoaSua("INSERT INTO HOADON VALUES(null, '"+v_manguoidung+"', "+v_mattgh+", '"+LayThoiGianHienTai()+"','"+v_tongtien+"',1)");
        Cursor v_cursor2 = Xem("SELECT last_insert_rowid()");
        int v_key = -1;
        while(v_cursor2.moveToNext()){
            v_key = v_cursor2.getInt(0);
        }
        for(int i = 0; i < a.size(); i++){
            ThemXoaSua("INSERT INTO CTHD VALUES('"+v_key+"','"+a.get(i).getMaSach()+"','"+a.get(i).getSoLuong()+"','"+a.get(i).getGia()+"')");
        }
    }

    /*public void XoaHoaDon(String v_mahd){
        Cursor v_cursor = Xem("SELECT MaTTGH FROM HoaDon WHERE MAHD='"+v_mahd+"'");
        int v_mattgh = 0;
        while(v_cursor.moveToNext()){
            v_mattgh = v_cursor.getInt(0);
        }
        ThemXoaSua("DELETE FROM CTHD WHERE MaHD='"+v_mahd+"'");
        ThemXoaSua("DELETE FROM HoaDon WHERE MaHD='"+v_mahd+"'");
        ThemXoaSua("DELETE FROM ThongTinGiaoHang WHERE MaTTGH='"+v_mattgh+"'");
    }*/

    public int LayTrangThaiHoaDon(String v_mahd){
        Cursor v_cursor = Xem("SELECT TrangThai FROM HoaDon WHERE MAHD='"+v_mahd+"'");
        int v_trangthai = 0;
        while(v_cursor.moveToNext()){
            v_trangthai = v_cursor.getInt(0);
        }
        return v_trangthai;
    }

    public void HuyHoaDon(String v_mahd){
        ThemXoaSua("UPDATE HoaDon set TrangThai=0 WHERE MaHD='"+v_mahd+"'");
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
        db.execSQL("Create table ThongTinGiaoHang(\n" +
                "MaTTGH INTEGER primary key AUTOINCREMENT,\n" +
                "HoTen varchar(100),\n" +
                "SDT varchar(10)," +
                "DiaChi varchar(100),\n" +
                "GhiChu varchar(100)\n" +
                ");");
        db.execSQL("Create table  HoaDon(\n" +
                "\tMaHD INTEGER primary key AUTOINCREMENT,\n" +
                "\tMaNguoiDung INTEGER not null,\n" +
                "\tMaTTGH INTEGER not null,\n" +
                "\tNgayXuat datetime,\n" +
                "\tThanhTien decimal(8,2),\n" +
                "\tTrangThai INTEGER,\n" +
                "\tFOREIGN key(MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung), \n" +
                "\tFOREIGN key(MaTTGH) REFERENCES ThongTinGiaoHang(MaTTGH)\n" +
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
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Conan tập 96', "+R.drawable.conan_tap_96+" , 'Kaito Kid muốn giành lấy “Đôi Môi Tiên Nữ” và lần đầu đối mặt với Heiji Hattori!\n\n" +
                "Makoto Kyogoku bị cuốn vào những vụ án xảy ra tại một địa điểm quay phim truyền hình…!?\n\n" +
                "Những thông tin mới nhất liên quan tới ông trùm của Tổ chức Áo Đen sẽ được tiết lộ!!', 20000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Nghĩ giàu làm giàu', "+R.drawable.nghi_giau_lam_giau+" , 'Think and Grow Rich - Nghĩ giàu và làm giàu là một trong những cuốn sách bán chạy nhất mọi thời đại. Đã hơn 60 triệu bản được phát hành với gần trăm ngôn ngữ trên toàn thế giới và được công nhận là cuốn sách tạo ra nhiều triệu phú, một cuốn sách truyền cảm hứng thành công nhiều hơn bất cứ cuốn sách kinh doanh nào trong lịch sử.', 40000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Doraemon tập 1', "+R.drawable.doraemon_tap_1+" , 'Nội dung series kể về cuộc đời bất hạnh của cậu bé Nobita và chú mèo robot Doraemon từ tương lai đến giúp cuộc sống cậu trở nên tốt hơn.', 10000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Thanh Gươm Diệt Quỷ - Kimetsu No Yaiba - Tập 14: Mu Trong Muichiro', "+R.drawable.thanh_guom_diet_quy_14+" , 'Con quỷ phân tách thành các cảm xúc - Hantengu - đã bỏ bản thể chính, chỉ đem những phân thân đã hợp nhất quay sang tấn công Tanjiro! Bị Hantengu sỉ nhục là “yếu đuối”, Tanjiro đang chiến đấu liệu có thấy tức giận!? Mặt khác, Hà trụ Tokito dần lấy lại kí ức và tiếp tục đối đầu Gyokko. Lúc ấy, trong Tokito đã có điều gì bắt đầu thay đổi…', 25000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Thanh Gươm Diệt Quỷ - Kimetsu No Yaiba - Tập 10: Người Và Quỷ', "+R.drawable.thanh_guom_diet_quy_10+" , 'Con quỷ oiran có tên Daki đang thống trị cả khu phố hoa! Sau khi thu hồi lại obi đang nắm giữ một phần sức mạnh, Daki quay ra tấn công Tanjiro với 100% sức lực!! Tanjiro cũng đáp lại bằng điệu Hỏa thần thần lạc, nhưng liệu đã quá giới hạn của cậu!? Nezuko và Uzui cũng tham chiến thay cho Tanjiro, trận chiến với quỷ Thượng huyền đã lên đến hồi cao trào và những diễn biến không thể đoán trước…!!', 25000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Black Clover - Tập 15: Kẻ Thắng Trận', "+R.drawable.black_clover_15+" , 'Kì thi Vương tuyển kị sĩ đoàn đã đến hồi cao trào! Trong cuộc hỗn chiến đang nổ ra, đội nào sẽ giành được vương miện chiến thắng? Liệu Asta và Yuno có vượt qua kì thi và gia nhập đội quân tinh nhuệ? Dưới sự chỉ đạo của Quốc vương và Ma pháp đế, đội quân Vương tuyển kị sĩ đoàn đã lên đường nghênh đón những cuộc chiến mới chống lại “Bạch dạ ma nhãn”!', 25000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Bleach - Tập 40: The Lust', "+R.drawable.bleach_40+" , 'Ichigo và Ulquiorra tái đấu!! Trước đây Ichigo từng thất bại trước sức mạnh áp đảo của Ulquiorra, nhưng để giải cứu Orihime khỏi căn cứ địch Las Noches, Ichigo đã không ngần ngại tuyên chiến với một ý chí bất khuất! Trước uy thế đó của Ichigo, Ulquiorra cuối cùng cũng đã phô diễn sức mạnh thật sự của mình…!?', 22000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Khi Tế Bào Làm Việc - Tập 4', "+R.drawable.khitebaolamviec_4+" , 'Hồng cầu lần đầu gánh trách nhiệm cao cả - hướng dẫn hồng cầu lính mới làm việc! Vậy là lại một thế hệ tế bào máu ra đời, cuộc sống trong mạch máu vẫn diễn ra bình thường như bao ngày. Cho tới khi…', 35000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Dragon Ball Full Color - Phần Một: Thời Niên Thiếu Của Son Goku - Tập 7', "+R.drawable.dragon_ball_full_color_7+" , 'DRAGON BALL là một Manga kinh điển với tầm ảnh hưởng lớn đến mức nào! Một bộ truyện chúng ta đã gắn bó suốt từ nhỏ, ăn cùng, ngủ cùng, bắt gặp hình ảnh ở khắp mọi nơi, vậy thì chẳng có lí do gì mà không sở hữu 1 phiên bản rực rỡ sắc màu như thế này cả! Nhất là khi \"Giấc mơ thuở bé\" nay đã thành sự thực nữa!', 77000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Hồi Kí Vanitas - Tập 8', "+R.drawable.hoi_ki_vanitas_8+" , 'Âm hưởng phục thù vang vọng trong vụ việc “Quái thú Gévaudan” bất chợt tan tác, những thay đổi nào sẽ đón chờ nhóm Vanitas ở nơi tận cùng bão tuyết đây...\n\nThời khắc lựa chọn đã điểm, cùng với đó là muôn vàn nỗi niềm khó thốt ra thành lời.', 36000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Công Chúa Sứa - Tập 16', "+R.drawable.cong_chua_sua_16+" , 'Chuyện về một cô bé đam mê sứa, sống giữa những người bạn có biệt danh là Amars - một dạng \"Ni cô\" trong lối sống của những người trẻ. Và rồi cô gặp một chàng trai sành điệu, người sau khi tiếp xúc đã có ý định xới tung cả hội Amars để thay đổi hình tượng những hủ nữ đang ẩn mình trong đó…\n\nTruyện nhẹ nhàng, sâu sắc phù hợp với thanh thiếu niên.', 17000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Score Maximizing For The TOEFL® iBT - Speaking And Writing', "+R.drawable.toefl+" , 'TOEFL iBT được coi là “tấm hộ chiếu” mở ra cánh cửa du học khắp năm châu.\n\n“Score Maximizing for the TOEFL® iBT – Speaking and Writing” là một trong những cuốn sách nổi tiếng trên toàn thế giới và tại Việt Nam được biên soạn bởi giáo sư Bruce Stirling.\n\nCuốn sách sẽ trang bị những kiến thức hữu ích:', 149000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Hoàng Tử Bé - Tập 8: Hành Tinh Rùa Khổng Lồ', "+R.drawable.hoang_tu_be_8+" , 'Điểm dừng chân tiếp theo trong chuyến phiêu lưu của Hoàng Tử Bé là hành tinh của Rùa khổng lồ. Nơi đây đang gặp rắc rối lớn khi những cư dân không thể gửi thư đến người thân của họ, liên lạc với bên ngoài hoàn toàn bị cắt đứt.\n\nNguyên nhân của tai ương này là sự biến mất của những chú Rùa khổng lồ - sinh vật duy nhất có thể di chuyển trên hành tinh với nhiệm vụ đưa thư. Hoàng Tử Bé cùng Cáo lại bắt đầu cuộc hành trình tìm kiếm những chú Rùa và khám ra câu chuyện đằng sau nó.', 49000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Nhật Ký Chú Bé Nhút Nhát - Tập 8', "+R.drawable.chu_be_nhut_nhat_8+" , 'Greg Heffley đang gặp vận xui liên miên. Cậu bạn thân Rowley Jeffferson đã bỏ rơi Greg, và việc tìm bạn mới ở trường trung học là một nhiệm vụ vô cùng gian nan. Để đổi vận, Greg quyết định tin tưởng vào trò may rủi. Liệu một vòng quay xúc xắc có thể hoán chuyển mọi thứ không, hay cuộc sống của Greg lại rẽ sang một hướng “nhọ\" khác? ', 50000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Nhật Ký Chú Bé Nhút Nhát - Những Ngày Xưa Cũ - Tập 10', "+R.drawable.chu_be_nhut_nhat_10+" , 'Ngày xưa cuộc sống tốt đẹp tươi vui hơn bây giờ nhiều. Đúng thế không nhỉ?\n\nĐó là câu hỏi mà Greg Heffley luôn trăn trở khi thị trấn của cậu tình nguyện rút hết các phích điện ra và nói \"không\" với các đồ điện tử. Nhưng cuộc sống hiện đại có những tiện lợi của nó, và Greg khó mà thích nghi được với kiểu sống ngày xưa.\n\nCăng thẳng dâng lên bên trong lẫn bên ngoài gia đình Heffley, liệu Greg có tìm ra đường sống sót? Hay lối sống \"cổ lỗ sĩ\" kia quá khó khăn với một đứa trẻ như cậu? Câu trả lời trong Tập 10 Nhật ký chú bé nhút nhát – Nhưng ngày xưa cũ.', 50000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Peter Pan', "+R.drawable.peter_pan+" , 'Peter Pan sống ở Neverland, hòn đảo xinh đẹp, sống động nhưng cũng đầy bí ẩn dưới sương mù của trí nhớ và mộng mơ. Ở Neverland, lũ trẻ đi lạc chung sống với những cô cậu tiên bé xíu, với thổ dân da đỏ, dã thú, tiên cá và cả hải tặc. Ở đó thời gian dường như đứng im, chỉ có những cuộc rượt bắt cứ xoay vòng, xoay vòng.\n\nMột lần đi chơi xa, Peter gặp cô bé Wendy mê mẩn những chuyện thần tiên. Xiêu lòng trước những lời rủ rê của Peter, Wendy đã cùng cậu bay đến hòn đảo kì diệu, hăm hở như mọi đứa trẻ trên đời lần đầu được tận mắt thấy phép mầu xảy ra. Để rồi, hai bạn nhỏ cùng nhau bước vào một cuộc phiêu lưu đầy biến cố…', 350000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Bé Tập Kể Chuyện - Cô Bé Quàng Khăn Đỏ', "+R.drawable.co_be_quang_khan_do+" , 'Bé Tập Kể Chuyện - Cô Bé Quàng Khăn Đỏ Cô bé quàng khăn đỏ là truyện cổ tích thế giới nổi tiếng được thể hiện bằng hình thức lược truyện cùng tranh minh họa màu sinh động giúp các em thêm phần thú vị khi đọc những câu chuyện cổ tích.', 10000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Tôi Vẽ - Phương Pháp Tự Học Vẽ Truyện Tranh', "+R.drawable.toi_ve_phuong_phap_tu_hoc_ve_truyen_tranh+" , '“Tôi vẽ với 300 trang sách bao gồm những kỹ năng cơ bản cần có của một họa sĩ truyện tranh, từ tạo hình nhân vật, thiết kế bối cảnh, biểu cảm, các kỹ thuật diễn họa cho đến luật phối cảnh. Đây là một cuốn cẩm nang tuyệt vời dành cho các bạn đang bắt đầu học vẽ truyện tranh. Những kiến thức này có thể không giúp các bạn vẽ đẹp ngay lập tức nhưng sẽ là nền tảng vững chắc giúp bạn hình thành các tiêu chuẩn chuyên nghiệp trong nghề và không mất thời gian tự mò mẫm. Phần minh họa cho các bài học cũng rất hấp dẫn và sáng tạo. Các tác giả đã sử dụng chính nhân vật và trang truyện của mình để làm rõ sự liên quan giữa lý thuyết và thực tế, tính ứng dụng rõ ràng của các kỹ thuật và quy trình sáng tác.', 100000 );");
        db.execSQL("INSERT INTO Sach (TenSach, ImgURL, MoTa, Gia) VALUES ('Dán Hình Thông Thái - Tổng Thống Mỹ', "+R.drawable.dan_hinh_thong_thai_tong_thong_my+" , 'Với tập sách này, em sẽ được tham quan Phòng Bầu dục – nơi các tổng thống Mỹ làm việc, điểm danh các đời tổng thống Mỹ hay được biết chuyên cơ Không lực Một – Nhà Trắng trên không… 60 hình dán sinh động sẽ khiến em thêm yêu thích tìm hiểu chủ đề này.', 36000 );");

        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Self-help');");
        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Truyện tranh');");
        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Tiểu thuyết');");
        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Phiêu lưu');");
        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Kỳ bí');");
        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Kinh dị');");
        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Mẹo vặt');");
        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Giáo dục');");
        db.execSQL("INSERT INTO TheLoaiSach (TenTheLoai) VALUES ('Vui nhộn');");

        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (3,1);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (1,2);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,3);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (1,4);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,5);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,6);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (6,6);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,7);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (6,7);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,8);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,9);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,10);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (9,10);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,11);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,12);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,13);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (8,14);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (4,15);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (9,16);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (9,17);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (3,18);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (4,18);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (5,18);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (2,19);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (8,20);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (8,21);");
        db.execSQL("INSERT INTO TheLoai (MaTheLoai,MaSach) VALUES (9,21);");

        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Nguyễn Du');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Anthony Robbins');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Gosho Aoyama');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Napoleon Hill');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Fujiko F. Fujio');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Koyoharu Gotouge');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Yuuki Tabata');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Tite Kubo');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Akane Shimizu');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Akira Toriyama');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Jun Mochizuki');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Akiko Higashimura');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Bruce Stirling');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Nhiều Tác Giả');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Jeff Kinney');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('J M Barrie');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('MinaLima');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Đức Lâm');");
        db.execSQL("INSERT INTO TacGia (TenTacGia) VALUES ('Hà Ly');");

        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (1,1);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (2,2);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (3,3);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (4,4);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (5,5);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (6,6);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (7,6);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (8,7);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (9,8);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (10,9);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (11,10);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (12,11);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (13,12);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (14,13);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (15,14);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (16,15);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (17,15);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (18,16);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (18,17);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (19,18);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (20,14);");
        db.execSQL("INSERT INTO TacGiaSach (MaSach,MaTacGia) VALUES (21,19);");

        db.execSQL("INSERT INTO NguoiDung (TenNguoiDung,TenDangNhap,MatKhau,DiaChi,SDT,LoaiNguoiDung) VALUES ('Đỗ Minh Nhựt','nhut','matkhau1','KTX B',0123456701,1);");
        db.execSQL("INSERT INTO NguoiDung (TenNguoiDung,TenDangNhap,MatKhau,DiaChi,SDT,LoaiNguoiDung) VALUES ('Anh Khoa','khoa','matkhau2','KTX B',0123478901,1);");
        db.execSQL("INSERT INTO NguoiDung (TenNguoiDung,TenDangNhap,MatKhau,DiaChi,SDT,LoaiNguoiDung) VALUES ('Công Hoàng','hoang','matkhau3','NHA TRO',0123445601,1);");
        db.execSQL("INSERT INTO NguoiDung (TenNguoiDung,TenDangNhap,MatKhau,DiaChi,SDT,LoaiNguoiDung) VALUES ('Ronaldo','nguongdung1','matkhau4','QUAN 1',0123412301,0);");
        db.execSQL("INSERT INTO NguoiDung (TenNguoiDung,TenDangNhap,MatKhau,DiaChi,SDT,LoaiNguoiDung) VALUES ('Messi','nguoidung2','matkhau5','QUAN 2',0235456701,0);");
        db.execSQL("INSERT INTO NguoiDung (TenNguoiDung,TenDangNhap,MatKhau,DiaChi,SDT,LoaiNguoiDung) VALUES ('Ronaldinho','nguoidung3','matkhau6','QUAN 3',0573456701,0);");

        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NgayBinhLuan,NoiDung) VALUES (1,4,'2020/04/10 07:27:35','Hay quá.');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NgayBinhLuan,NoiDung) VALUES (2,5,'2020/04/10 07:27:35','Hay ghê.');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NgayBinhLuan,NoiDung) VALUES (3,3,'2020/04/10 07:27:35','Mai mua thêm cuốn.');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NgayBinhLuan,NoiDung) VALUES (4,2,'2020/04/10 07:27:35','Tác giả chém gió hay thật');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NgayBinhLuan,NoiDung) VALUES (5,1,'2020/04/10 07:27:35','Hay quá xá.');");
        db.execSQL("INSERT INTO BinhLuan (MaNguoiDung,MaSach,NgayBinhLuan,NoiDung) VALUES (6,4,'2020/04/10 07:27:35','Vừa tào lao mà cũng vừa có lý :v');");

        db.execSQL("INSERT INTO ThongTinGiaoHang VALUES (null, 'Đỗ Minh Nhựt', '0123456701', 'KTX B', 'Giao vào buổi sáng nhé!');");
        db.execSQL("INSERT INTO ThongTinGiaoHang VALUES (null, 'Anh Khoa', '0123478901', 'KTX B', 'Giao lẹ lẹ lên nha.');");
        db.execSQL("INSERT INTO ThongTinGiaoHang VALUES (null, 'Công Hoàng', '0123445601', 'NHA TRO', 'Không biết ghi gì.');");
        db.execSQL("INSERT INTO ThongTinGiaoHang VALUES (null, 'Ronaldo', '0123412301', 'QUAN 1', 'Sao cũng được.');");
        db.execSQL("INSERT INTO ThongTinGiaoHang VALUES (null, 'Messi', '0235456701', 'QUAN 2', 'Ghi chú ghi chú ghi chú');");
        db.execSQL("INSERT INTO ThongTinGiaoHang VALUES (null, 'Ronaldinho', '0573456701', 'QUAN 3', 'Giao lẹ lên!');");

        db.execSQL("INSERT INTO HoaDon (MaNguoiDung,MaTTGH,NgayXuat,ThanhTien, TrangThai) VALUES (1,1,'2020/04/10 07:27:35', 20000.00, 3);");
        db.execSQL("INSERT INTO HoaDon (MaNguoiDung,MaTTGH,NgayXuat,ThanhTien, TrangThai) VALUES (2,2,'2020/05/20 08:27:09', 30000.00, 3);");
        db.execSQL("INSERT INTO HoaDon (MaNguoiDung,MaTTGH,NgayXuat,ThanhTien, TrangThai) VALUES (3,3,'2020/06/25 15:27:19', 500000.00, 3);");
        db.execSQL("INSERT INTO HoaDon (MaNguoiDung,MaTTGH,NgayXuat,ThanhTien, TrangThai) VALUES (4,4,'2020/07/14 17:27:29', 10000.00, 3);");
        db.execSQL("INSERT INTO HoaDon (MaNguoiDung,MaTTGH,NgayXuat,ThanhTien, TrangThai) VALUES (5,5,'2020/08/23 20:27:30', 10000.00, 3);");
        db.execSQL("INSERT INTO HoaDon (MaNguoiDung,MaTTGH,NgayXuat,ThanhTien, TrangThai) VALUES (6,6,'2020/09/09 08:27:40', 40000.00, 3);");

        db.execSQL("INSERT INTO CTHD (MaHD,MaSach,SoLuong,DonGia) VALUES (1,3,1,20000.00);");
        db.execSQL("INSERT INTO CTHD (MaHD,MaSach,SoLuong,DonGia) VALUES (3,1,1,500000.00);");
        db.execSQL("INSERT INTO CTHD (MaHD,MaSach,SoLuong,DonGia) VALUES (2,2,1,30000.00);");
        db.execSQL("INSERT INTO CTHD (MaHD,MaSach,SoLuong,DonGia) VALUES (4,5,1,10000.00);");
        db.execSQL("INSERT INTO CTHD (MaHD,MaSach,SoLuong,DonGia) VALUES (6,4,1,40000.00);");
        db.execSQL("INSERT INTO CTHD (MaHD,MaSach,SoLuong,DonGia) VALUES (5,5,1,10000.00);");
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
