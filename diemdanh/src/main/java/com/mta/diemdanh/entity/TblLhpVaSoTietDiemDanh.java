package com.mta.diemdanh.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbllophocphanvasotietdiemdanh")
public class TblLhpVaSoTietDiemDanh {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma_lop_hoc_phan")
    private String maLopHocPhan;

    @Column(name = "tong_so_tiet_diem_danh")
    private Float tongSoTietDiemDanh;

    public TblLhpVaSoTietDiemDanh() {
    }

    public TblLhpVaSoTietDiemDanh(int id, String maLopHocPhan, Float tongSoTietDiemDanh) {
        this.id = id;
        this.maLopHocPhan = maLopHocPhan;
        this.tongSoTietDiemDanh = tongSoTietDiemDanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaLopHocPhan() {
        return maLopHocPhan;
    }

    public void setMaLopHocPhan(String maLopHocPhan) {
        this.maLopHocPhan = maLopHocPhan;
    }

    public Float getTongSoTietDiemDanh() {
        return tongSoTietDiemDanh;
    }

    public void setTongSoTietDiemDanh(Float tongSoTietDiemDanh) {
        this.tongSoTietDiemDanh = tongSoTietDiemDanh;
    }
}
