package com.mta.diemdanh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblgiaovien")
public class TblGiaoVien {
    @Id
    @Column(name = "ma_giao_vien")
    private String maGiaoVien;

    @Column(name = "ten_giao_vien")
    private String tenGiaoVien;

    @Column(name = "hoc_ham")
    private String hocHam;

    @Column(name = "hoc_vi")
    private String hocVi;

    @Column(name = "ma_khoa")
    private String maKhoa;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    public TblGiaoVien() {
    }

    public TblGiaoVien(String maGiaoVien, String tenGiaoVien, String hocHam, String hocVi,
                       String maKhoa, String soDienThoai, String email) {
        this.maGiaoVien = maGiaoVien;
        this.tenGiaoVien = tenGiaoVien;
        this.hocHam = hocHam;
        this.hocVi = hocVi;
        this.maKhoa = maKhoa;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public String getMaGiaoVien() {
        return maGiaoVien;
    }

    public void setMaGiaoVien(String maGiaoVien) {
        this.maGiaoVien = maGiaoVien;
    }

    public String getTenGiaoVien() {
        return tenGiaoVien;
    }

    public void setTenGiaoVien(String tenGiaoVien) {
        this.tenGiaoVien = tenGiaoVien;
    }

    public String getHocHam() {
        return hocHam;
    }

    public void setHocHam(String hocHam) {
        this.hocHam = hocHam;
    }

    public String getHocVi() {
        return hocVi;
    }

    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
