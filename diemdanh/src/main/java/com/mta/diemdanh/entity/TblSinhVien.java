package com.mta.diemdanh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "tblsinhvien")
public class TblSinhVien {
    @Id
    @Column(name = "ma_sinh_vien")
    private String maSinhVien;

    @Column(name = "ten_sinh_vien")
    private String tenSinhVien;

    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "dan_toc")
    private String danToc;

    @Column(name = "quoc_gia")
    private String quocGia;

    @Column(name = "nguyen_quan")
    private String nguyenQuan;

    @Column(name = "cho_o_hien_nay")
    private String choOHienNay;

    @Column(name = "email")
    private String email;

    @Column(name = "ma_lop_chuyen_nganh")
    private String maChuyenNganh;

    public TblSinhVien() {
    }

    public TblSinhVien(String maSinhVien, String tenSinhVien, Date ngaySinh,
                       String danToc, String quocGia, String nguyenQuan,
                       String choOHienNay, String email, String maChuyenNganh) {
        this.maSinhVien = maSinhVien;
        this.tenSinhVien = tenSinhVien;
        this.ngaySinh = ngaySinh;
        this.danToc = danToc;
        this.quocGia = quocGia;
        this.nguyenQuan = nguyenQuan;
        this.choOHienNay = choOHienNay;
        this.email = email;
        this.maChuyenNganh = maChuyenNganh;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getNguyenQuan() {
        return nguyenQuan;
    }

    public void setNguyenQuan(String nguyenQuan) {
        this.nguyenQuan = nguyenQuan;
    }

    public String getChoOHienNay() {
        return choOHienNay;
    }

    public void setChoOHienNay(String choOHienNay) {
        this.choOHienNay = choOHienNay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaChuyenNganh() {
        return maChuyenNganh;
    }

    public void setMaChuyenNganh(String maChuyenNganh) {
        this.maChuyenNganh = maChuyenNganh;
    }
}
