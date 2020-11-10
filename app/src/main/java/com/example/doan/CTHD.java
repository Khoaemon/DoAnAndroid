package com.example.doan;

public class CTHD {
    private int MaSach;
    private String TenSach;
    private int SoLuong;
    private double DonGia;

    public CTHD(int maSach, String tenSach, int soLuong, double donGia) {
        MaSach = maSach;
        TenSach = tenSach;
        SoLuong = soLuong;
        DonGia = donGia;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double donGia) {
        DonGia = donGia;
    }
}
