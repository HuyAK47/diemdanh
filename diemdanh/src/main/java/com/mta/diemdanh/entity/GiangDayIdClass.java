package com.mta.diemdanh.entity;

import java.io.Serializable;

public class GiangDayIdClass implements Serializable {
    private String maGiaoVien;
    private String maLopHocPhan;

    public GiangDayIdClass(String maGiaoVien, String maLopHocPhan) {
        this.maGiaoVien = maGiaoVien;
        this.maLopHocPhan = maLopHocPhan;
    }
}
