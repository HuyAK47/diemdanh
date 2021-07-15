package com.mta.diemdanhandroid.entity;

public class DiemEntity {
    private int id;
    private String maSinhVien;
    private String maLopHocPhan;
    private Float diemChuyenCan;
    private Float diemThuongXuyen;
    private Float diemThi;
    private SinhVienEntity sinhVien;

    public DiemEntity() {
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

    public String diemCCtoString(){
        if(diemChuyenCan != null){
            String diemCC = String.valueOf(diemChuyenCan).substring(0,3);
            if(Float.parseFloat(diemCC) >= 10.0){
                return "10";
            }
            return String.valueOf(diemChuyenCan).substring(0,3);
        }
        return "";
    }

    public String diemTXtoString(){
        if(diemThuongXuyen != null){
            return String.valueOf(diemThuongXuyen).substring(0,3);
        }
        return "";
    }

    public String diemThiToString(){
        if(diemThi != null){
            return String.valueOf(diemThi).substring(0,3);
        }
        return "";
    }

    public SinhVienEntity getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVienEntity sinhVien) {
        this.sinhVien = sinhVien;
    }
}
