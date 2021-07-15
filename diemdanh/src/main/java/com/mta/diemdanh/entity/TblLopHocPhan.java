package com.mta.diemdanh.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tbllophocphan")
public class TblLopHocPhan {
    @Id
    @Column(name = "ma_lop_hoc_phan")
    private String maLopHocPhan;

    // MTA HuyNQn xu ly khoa phu
    @Column(name = "ma_hoc_phan")
    private String maHocPhan;

    // MTA HuyNQn xu ly khoa phu
    @Column(name = "ma_hoc_ky")
    private String maHocKy;

    @Column(name = "si_so")
    private int siSo;

    @Column(name = "phong_hoc")
    private String phongHoc;

    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private Date ngayKetThuc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ma_hoc_phan", referencedColumnName = "ma_hoc_phan", insertable= false, updatable=false)
    private TblHocPhan hocPhan;

    public TblLopHocPhan() {
    }

    public TblLopHocPhan(String maLopHocPhan, String maHocPhan, String maHocKy,
                         int siSo, String phongHoc, Date ngayBatDau, Date ngayKetThuc) {
        this.maLopHocPhan = maLopHocPhan;
        this.maHocPhan = maHocPhan;
        this.maHocKy = maHocKy;
        this.siSo = siSo;
        this.phongHoc = phongHoc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public TblLopHocPhan(String maLopHocPhan, String maHocPhan, String maHocKy, int siSo,
                         String phongHoc, Date ngayBatDau, Date ngayKetThuc, TblHocPhan hocPhan) {
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

    public TblHocPhan getHocPhan() {
        return hocPhan;
    }

    public void setHocPhan(TblHocPhan hocPhan) {
        this.hocPhan = hocPhan;
    }
}
