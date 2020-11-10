package com.example.doan;

public class HoaDon {
    private int MaHD;
    private String NgayXuat;
    private double ThanhTien;

    public HoaDon(int maHD, String ngayXuat, double thanhTien) {
        MaHD = maHD;
        NgayXuat = ngayXuat;
        ThanhTien = thanhTien;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public String getNgayXuat() {
        return NgayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        NgayXuat = ngayXuat;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double thanhTien) {
        ThanhTien = thanhTien;
    }
}
