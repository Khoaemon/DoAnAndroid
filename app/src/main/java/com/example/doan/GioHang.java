package com.example.doan;

public class GioHang {
    private int MaSach;
    private String TenSach;
    private int Img;
    private double Gia;
    private int SoLuong;

    public GioHang(int maSach, String tenSach, int img, double gia, int soLuong) {
        MaSach = maSach;
        TenSach = tenSach;
        Img = img;
        Gia = gia;
        SoLuong = soLuong;
    }

    public GioHang(int maSach, int soLuong) {
        MaSach = maSach;
        SoLuong = soLuong;
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

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double gia) {
        Gia = gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}
