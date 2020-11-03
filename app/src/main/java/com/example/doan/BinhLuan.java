package com.example.doan;

public class BinhLuan {
    private String TenNguoiDung;
    private String NoiDung;

    public BinhLuan(String tenNguoiDung, String noiDung) {
        TenNguoiDung = tenNguoiDung;
        NoiDung = noiDung;
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
}
