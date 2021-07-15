package com.mta.diemdanh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblbomon")
public class TblBoMon {
    @Id
    @Column(name = "ma_bo_mon")
    private String maBoMon;

    @Column(name = "ten_bo_mon")
    private String tenBoMon;

    @Column(name = "ma_khoa")
    private String maKhoa;

    public TblBoMon() {
    }

    public TblBoMon(String maBoMon, String tenBoMon, String maKhoa) {
        this.maBoMon = maBoMon;
        this.tenBoMon = tenBoMon;
        this.maKhoa = maKhoa;
    }

    public String getMaBoMon() {
        return maBoMon;
    }

    public void setMaBoMon(String maBoMon) {
        this.maBoMon = maBoMon;
    }

    public String getTenBoMon() {
        return tenBoMon;
    }

    public void setTenBoMon(String tenBoMon) {
        this.tenBoMon = tenBoMon;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }
}
