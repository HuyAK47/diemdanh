package com.mta.diemdanhandroid.entity;

public class GiaoVienEntity {
    private String maGiaoVien;
    private String tenGiaoVien;
    private String hocHam;
    private String hocVi;
    private String maKhoa;
    private String soDienThoai;
    private String email;

    public GiaoVienEntity() {
    }

    public GiaoVienEntity(String maGiaoVien, String tenGiaoVien, String hocHam, String hocVi,
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
