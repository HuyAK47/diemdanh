package com.mta.diemdanhandroid.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DiemDanhEntity {
    private int id;
    private String maSinhVien;
    private String maLopHocPhan;
    private Date ngayDiemDanh;
    private String macDevice;
    private String kieuDiemDanh;
    private String kieuTietHoc;

    public DiemDanhEntity() {
    }

    public DiemDanhEntity(int id, String maSinhVien, String maLopHocPhan,
                          Date ngayDiemDanh, String macDevice, String kieuDiemDanh, String kieuTietHoc) {
        this.id = id;
        this.maSinhVien = maSinhVien;
        this.maLopHocPhan = maLopHocPhan;
        this.ngayDiemDanh = ngayDiemDanh;
        this.macDevice = macDevice;
        this.kieuDiemDanh = kieuDiemDanh;
        this.kieuTietHoc = kieuTietHoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getNgayDiemDanh() {
        return ngayDiemDanh;
    }

    public void setNgayDiemDanh(Date ngayDiemDanh) {
        this.ngayDiemDanh = ngayDiemDanh;
    }

    public String getMacDevice() {
        return macDevice;
    }

    public void setMacDevice(String macDevice) {
        this.macDevice = macDevice;
    }

    public String getKieuDiemDanh() {
        return kieuDiemDanh;
    }

    public void setKieuDiemDanh(String kieuDiemDanh) {
        this.kieuDiemDanh = kieuDiemDanh;
    }

    public String getKieuTietHoc() {
        return kieuTietHoc;
    }

    public void setKieuTietHoc(String kieuTietHoc) {
        this.kieuTietHoc = kieuTietHoc;
    }

    public String getNgayDiemDanhToString(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(ngayDiemDanh);
        return date;
    }

}
