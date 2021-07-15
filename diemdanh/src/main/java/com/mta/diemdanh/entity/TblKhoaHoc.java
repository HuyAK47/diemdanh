package com.mta.diemdanh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "tblkhoahoc")
public class TblKhoaHoc {
    @Id
    @Column(name = "ma_khoa_hoc")
    private String maKhoaHoc;

    @Column(name = "ten_khoa_hoc")
    private String tenKhoaHoc;

    @Column(name = "nam_bat_dau")
    private Date namBatDau;

    @Column(name = "nam_ket_thuc")
    private Date namKetThuc;

    public TblKhoaHoc() {
    }

    public TblKhoaHoc(String maKhoaHoc, String tenKhoaHoc, Date namBatDau, Date namKetThuc) {
        this.maKhoaHoc = maKhoaHoc;
        this.tenKhoaHoc = tenKhoaHoc;
        this.namBatDau = namBatDau;
        this.namKetThuc = namKetThuc;
    }

    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(String maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public Date getNamBatDau() {
        return namBatDau;
    }

    public void setNamBatDau(Date namBatDau) {
        this.namBatDau = namBatDau;
    }

    public Date getNamKetThuc() {
        return namKetThuc;
    }

    public void setNamKetThuc(Date namKetThuc) {
        this.namKetThuc = namKetThuc;
    }
}
