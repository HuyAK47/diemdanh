package com.mta.diemdanhandroid.entity;

import android.text.TextUtils;

public class LhpVaSoTietDiemDanhEntity {
    private int id;
    private String maLopHocPhan;
    private Float tongSoTietDiemDanh;

    public LhpVaSoTietDiemDanhEntity() {
    }

    public LhpVaSoTietDiemDanhEntity(Float tongSoTietDiemDanh) {
        this.tongSoTietDiemDanh = tongSoTietDiemDanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaLopHocPhan() {
        return maLopHocPhan;
    }

    public void setMaLopHocPhan(String maLopHocPhan) {
        this.maLopHocPhan = maLopHocPhan;
    }

    public Float getTongSoTietDiemDanh() {
        return tongSoTietDiemDanh;
    }

    public void setTongSoTietDiemDanh(Float tongSoTietDiemDanh) {
        this.tongSoTietDiemDanh = tongSoTietDiemDanh;
    }

    public String getTongSTDDtoString() {
        if(tongSoTietDiemDanh != null){
            return String.valueOf(tongSoTietDiemDanh).substring(0,3);
        }
        return "0";
    }
}
