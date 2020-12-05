package com.example.doan;

public class BinhLuan {
    private String TenNguoiDung;
    private String NoiDung;
    private String NgayBinhLuan;

    public BinhLuan(String tenNguoiDung, String noiDung, String ngayBinhLuan) {
        TenNguoiDung = tenNguoiDung;
        NoiDung = noiDung;
        NgayBinhLuan = ngayBinhLuan;
    }

    public String getTenNguoiDung() {
        return TenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        TenNguoiDung = tenNguoiDung;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getNgayBinhLuan() {
        return NgayBinhLuan;
    }

    public void setNgayBinhLuan(String ngayBinhLuan) {
        NgayBinhLuan = ngayBinhLuan;
    }
}
