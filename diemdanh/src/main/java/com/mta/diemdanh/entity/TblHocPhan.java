package com.mta.diemdanh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblhocphan")
public class TblHocPhan {
    @Id
    @Column(name = "ma_hoc_phan")
    private String maHocPhan;

    @Column(name = "ten_hoc_phan")
    private String tenHocPhan;

    @Column(name = "so_tin_chi")
    private int soTinChi;

    public TblHocPhan() {
    }

    public TblHocPhan(String maHocPhan, String tenHocPhan, int soTinChi) {
        this.maHocPhan = maHocPhan;
        this.tenHocPhan = tenHocPhan;
        this.soTinChi = soTinChi;
    }

    public String getMaHocPhan() {
        return maHocPhan;
    }

    public void setMaHocPhan(String maHocPhan) {
        this.maHocPhan = maHocPhan;
    }

    public String getTenHocPhan() {
        return tenHocPhan;
    }

    public void setTenHocPhan(String tenHocPhan) {
        this.tenHocPhan = tenHocPhan;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }
}
