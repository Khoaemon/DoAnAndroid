package com.example.doan;

public class HoaDon {
    private int MaHD;
    private String NgayXuat;
    private double ThanhTien;
    private int TrangThai;

    public HoaDon(int maHD, String ngayXuat, double thanhTien, int trangThai) {
        MaHD = maHD;
        NgayXuat = ngayXuat;
        ThanhTien = thanhTien;
        TrangThai = trangThai;
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

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }
}
