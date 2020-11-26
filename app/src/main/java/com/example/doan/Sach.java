package com.example.doan;

public class Sach {
    private int MaSach;
    private String TenSach;
    private int ImgURL;
    private String MoTa;
    private double Gia;

    public Sach(int maSach, String tenSach, int imgURL, String moTa, double gia) {
        MaSach = maSach;
        TenSach = tenSach;
        ImgURL = imgURL;
        MoTa = moTa;
        Gia = gia;
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

    public int getImgURL() {
        return ImgURL;
    }

    public void setImgURL(int imgURL) {
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
