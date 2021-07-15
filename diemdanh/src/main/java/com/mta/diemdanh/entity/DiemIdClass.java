package com.mta.diemdanh.entity;

import java.io.Serializable;

public class DiemIdClass implements Serializable {
    private String maSinhVien;
    private String maLopHocPhan;

    public DiemIdClass(String maSinhVien, String maLopHocPhan) {
        this.maSinhVien = maSinhVien;
        this.maLopHocPhan = maLopHocPhan;
    }
}
