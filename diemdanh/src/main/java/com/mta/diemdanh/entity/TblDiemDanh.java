package com.mta.diemdanh.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tbldiemdanh")
public class TblDiemDanh {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma_sinh_vien")
    private String maSinhVien;

    @Column(name = "ma_lop_hoc_phan")
    private String maLopHocPhan;

    @Column(name = "ngay_diem_danh")
    private Date ngayDiemDanh;

    @Column(name = "mac_device")
    private String macDevice;

    @Column(name = "kieu_diem_danh")
    private String kieuDiemDanh;

    @Column(name = "kieu_tiet_hoc")
    private String kieuTietHoc;

    public TblDiemDanh() {
    }

    public TblDiemDanh(int id, String maSinhVien, String maLopHocPhan,
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
}
