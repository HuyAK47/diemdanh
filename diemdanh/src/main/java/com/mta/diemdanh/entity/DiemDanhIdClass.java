package com.mta.diemdanh.entity;

import java.io.Serializable;

public class DiemDanhIdClass implements Serializable {
    private String maSinhVien;
    private String maLopHocPhan;

    public DiemDanhIdClass() {
    }

    public DiemDanhIdClass(String maSinhVien, String maLopHocPhan) {
        this.maSinhVien = maSinhVien;
        this.maLopHocPhan = maLopHocPhan;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getMaLopHocPhan() {
        return maLopHocPhan;
    }

    public void setMaLopHocPhan(String maLopHocPhan) {
        this.maLopHocPhan = maLopHocPhan;
    }
}
