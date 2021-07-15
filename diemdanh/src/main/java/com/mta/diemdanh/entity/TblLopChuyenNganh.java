package com.mta.diemdanh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbllopchuyennganh")
public class TblLopChuyenNganh {
    @Id
    @Column(name = "ma_lop_chuyen_nganh")
    private String maLopChuyenNganh;

    @Column(name = "ten_lop_chuyen_nganh")
    private String tenLopChuyenNganh;

    @Column(name = "ma_giao_vien")
    private String maGiaoVien;

    @Column(name = "ma_khoa")
    private String maKhoa;

    @Column(name = "ma_khoa_hoc")
    private String maKhoaHoc;

    public TblLopChuyenNganh() {
    }

    public TblLopChuyenNganh(String maLopChuyenNganh, String tenLopChuyenNganh,
                             String maGiaoVien, String maKhoa, String maKhoaHoc) {
        this.maLopChuyenNganh = maLopChuyenNganh;
        this.tenLopChuyenNganh = tenLopChuyenNganh;
        this.maGiaoVien = maGiaoVien;
        this.maKhoa = maKhoa;
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getMaLopChuyenNganh() {
        return maLopChuyenNganh;
    }

    public void setMaLopChuyenNganh(String maLopChuyenNganh) {
        this.maLopChuyenNganh = maLopChuyenNganh;
    }

    public String getTenLopChuyenNganh() {
        return tenLopChuyenNganh;
    }

    public void setTenLopChuyenNganh(String tenLopChuyenNganh) {
        this.tenLopChuyenNganh = tenLopChuyenNganh;
    }

    public String getMaGiaoVien() {
        return maGiaoVien;
    }

    public void setMaGiaoVien(String maGiaoVien) {
        this.maGiaoVien = maGiaoVien;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(String maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }
}
