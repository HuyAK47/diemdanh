package com.mta.diemdanhandroid.entity;

import java.util.Date;

public class LopHocPhanEntity {
    private String maLopHocPhan;
    private String maHocPhan;
    private String maHocKy;
    private int siSo;
    private String phongHoc;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private HocPhanEntity hocPhan;

    public LopHocPhanEntity() {
    }

    public LopHocPhanEntity(String maLopHocPhan, String maHocPhan, String maHocKy,
                            int siSo, String phongHoc, Date ngayBatDau, Date ngayKetThuc) {
        this.maLopHocPhan = maLopHocPhan;
        this.maHocPhan = maHocPhan;
        this.maHocKy = maHocKy;
        this.siSo = siSo;
        this.phongHoc = phongHoc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public LopHocPhanEntity(String maLopHocPhan, String maHocPhan, String maHocKy, int siSo,
                            String phongHoc, Date ngayBatDau, Date ngayKetThuc, HocPhanEntity hocPhan) {
        this.maLopHocPhan = maLopHocPhan;
        this.maHocPhan = maHocPhan;
        this.maHocKy = maHocKy;
        this.siSo = siSo;
        this.phongHoc = phongHoc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hocPhan = hocPhan;
    }

    public String getMaLopHocPhan() {
        return maLopHocPhan;
    }

    public void setMaLopHocPhan(String maLopHocPhan) {
        this.maLopHocPhan = maLopHocPhan;
    }

    public String getMaHocPhan() {
        return maHocPhan;
    }

    public void setMaHocPhan(String maHocPhan) {
        this.maHocPhan = maHocPhan;
    }

    public String getMaHocKy() {
        return maHocKy;
    }

    public void setMaHocKy(String maHocKy) {
        this.maHocKy = maHocKy;
    }

    public int getSiSo() {
        return siSo;
    }

    public void setSiSo(int siSo) {
        this.siSo = siSo;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public HocPhanEntity getHocPhan() {
        return hocPhan;
    }

    public void setHocPhan(HocPhanEntity hocPhan) {
        this.hocPhan = hocPhan;
    }
}
