package com.example.doan;

public class Sach {
    private int MaSach;
    private String TenSach;
    private String ImgURL;
    private String MoTa;
    private double Gia;
    private int SoLuong;

    public Sach(int maSach, String tenSach, String imgURL, String moTa, double gia, int soLuong) {
        MaSach = maSach;
        TenSach = tenSach;
        ImgURL = imgURL;
        MoTa = moTa;
        Gia = gia;
        SoLuong = soLuong;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
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

    public String getImgURL() {
        return ImgURL;
    }

    public void setImgURL(String imgURL) {
        ImgURL = imgURL;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double gia) {
        Gia = gia;
    }
}
