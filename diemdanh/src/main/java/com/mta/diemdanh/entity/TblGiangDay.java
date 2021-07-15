package com.mta.diemdanh.entity;

import javax.persistence.*;

@Entity
@Table(name = "tblgiangday")
public class TblGiangDay {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma_giao_vien")
    private String maGiaoVien;

    @Column(name = "ma_lop_hoc_phan")
    private String maLopHocPhan;

    public TblGiangDay() {
    }

    public TblGiangDay(int id, String maGiaoVien, String maLopHocPhan) {
        this.id = id;
        this.maGiaoVien = maGiaoVien;
        this.maLopHocPhan = maLopHocPhan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaGiaoVien() {
        return maGiaoVien;
    }

    public void setMaGiaoVien(String maGiaoVien) {
        this.maGiaoVien = maGiaoVien;
    }

    public String getMaLopHocPhan() {
        return maLopHocPhan;
    }

    public void setMaLopHocPhan(String maLopHocPhan) {
        this.maLopHocPhan = maLopHocPhan;
    }
}
