package com.mta.diemdanh.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbldiem")
public class TblDiem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma_sinh_vien")
    private String maSinhVien;

    @Column(name = "ma_lop_hoc_phan")
    private String maLopHocPhan;

    @Column(name = "diem_chuyen_can")
    private Float diemChuyenCan;

    @Column(name = "diem_thuong_xuyen")
    private Float diemThuongXuyen;

    @Column(name = "diem_thi")
    private Float diemThi;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ma_sinh_vien", referencedColumnName = "ma_sinh_vien", insertable = false, updatable = false)
    private TblSinhVien sinhVien;

    public TblDiem() {
    }

    public TblDiem(int id, String maSinhVien, String maLopHocPhan, Float diemChuyenCan, Float diemThuongXuyen, Float diemThi) {
        this.id = id;
        this.maSinhVien = maSinhVien;
        this.maLopHocPhan = maLopHocPhan;
        this.diemChuyenCan = diemChuyenCan;
        this.diemThuongXuyen = diemThuongXuyen;
        this.diemThi = diemThi;
    }

    public TblDiem(int id, String maSinhVien, String maLopHocPhan, Float diemChuyenCan,
                   Float diemThuongXuyen, Float diemThi, TblSinhVien sinhVien) {
        this.id = id;
        this.maSinhVien = maSinhVien;
        this.maLopHocPhan = maLopHocPhan;
        this.diemChuyenCan = diemChuyenCan;
        this.diemThuongXuyen = diemThuongXuyen;
        this.diemThi = diemThi;
        this.sinhVien = sinhVien;
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

    public Float getDiemChuyenCan() {
        return diemChuyenCan;
    }

    public void setDiemChuyenCan(Float diemChuyenCan) {
        this.diemChuyenCan = diemChuyenCan;
    }

    public Float getDiemThuongXuyen() {
        return diemThuongXuyen;
    }

    public void setDiemThuongXuyen(Float diemThuongXuyen) {
        this.diemThuongXuyen = diemThuongXuyen;
    }

    public Float getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(Float diemThi) {
        this.diemThi = diemThi;
    }

    public TblSinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(TblSinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }
}
