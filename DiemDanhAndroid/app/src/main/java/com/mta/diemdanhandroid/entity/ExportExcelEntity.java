package com.mta.diemdanhandroid.entity;

public class ExportExcelEntity {
    private String maSinhVien;
    private String tenSinhVien;
    private String diemChuyenCan;
    private String diemThuongXuyen;
    private String diemThi;

    public ExportExcelEntity() {
    }

    public ExportExcelEntity(String maSinhVien, String tenSinhVien, String diemChuyenCan,
                             String diemThuongXuyen, String diemThi) {
        this.maSinhVien = maSinhVien;
        this.tenSinhVien = tenSinhVien;
        this.diemChuyenCan = diemChuyenCan;
        this.diemThuongXuyen = diemThuongXuyen;
        this.diemThi = diemThi;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public String getDiemChuyenCan() {
        return diemChuyenCan;
    }

    public void setDiemChuyenCan(String diemChuyenCan) {
        this.diemChuyenCan = diemChuyenCan;
    }

    public String getDiemThuongXuyen() {
        return diemThuongXuyen;
    }

    public void setDiemThuongXuyen(String diemThuongXuyen) {
        this.diemThuongXuyen = diemThuongXuyen;
    }

    public String getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(String diemThi) {
        this.diemThi = diemThi;
    }
}
