package com.mta.diemdanh.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbltaikhoan")
public class TblTaiKhoan {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ten_dang_nhap")
    private String tenDangNhap;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "ma_quyen")
    private String maQuyen;

    @Column(name = "token")
    private String token;

    @Column(name = "rooted")
    private String rooted;

    public TblTaiKhoan() {
    }

    public TblTaiKhoan(int id, String tenDangNhap, String matKhau, String maQuyen, String token, String rooted) {
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maQuyen = maQuyen;
        this.token = token;
        this.rooted = rooted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRooted() {
        return rooted;
    }

    public void setRooted(String rooted) {
        this.rooted = rooted;
    }
}
