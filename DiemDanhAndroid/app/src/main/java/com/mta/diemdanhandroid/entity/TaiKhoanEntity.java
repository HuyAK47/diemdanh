package com.mta.diemdanhandroid.entity;

public class TaiKhoanEntity {
    private int id;
    private String tenDangNhap;
    private String matKhau;
    private String maQuyen;
    private String token;
    private String rooted;

    public TaiKhoanEntity() {
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
