package com.mta.diemdanhandroid.server;

public class ApiConnect {
//    public static final String LOCAL_HOST = "http:192.168.0.104:8088";
    public static final String LOCAL_HOST = "http:192.168.43.11:8088";

    public static final String API_LOGIN = LOCAL_HOST + "/login";
    public static final String API_CHANGE_PASSWORD = LOCAL_HOST + "/taik-khoan/doi-mat-khau";
    public static final String API_GET_SINH_VIEN_BY_ID = LOCAL_HOST + "/sinh-vien/{id}";
    public static final String API_GET_TKB_SINH_VIEN = LOCAL_HOST + "/tkb-sinh-vien/{id}";

    public static final String API_GET_DIEM_ONLY_STUDENT = LOCAL_HOST + "/diem/for-sinh-vien";
    public static final String API_GET_CHECK_IN_SUBJECT = LOCAL_HOST + "/sinh-vien/mon-hoc/ket-qua-diem-danh";


    public static final String API_GET_GIAO_VIEN_BY_ID = LOCAL_HOST + "/giao-vien/{id}";
    public static final String API_GET_TKB_GIAO_VIEN = LOCAL_HOST + "/tkb-giao-vien/{id}";
    public static final String API_GET_DANH_SACH_SINH_VIEN_LHP = LOCAL_HOST + "/lop-hoc-phan/danh-sach-sinh-vien/{id}";
    public static final String API_POST_LHP_DIEM_DANH = LOCAL_HOST + "/mon-hoc/lop-hoc-phan/thuc-hien-diem-danh";
    public static final String API_POST_XOA_BUOI_DIEM_DANH = LOCAL_HOST + "/mon-hoc/lop-hoc-phan/diem-danh/xoa/{id}";
    public static final String API_POST_CAP_NHAT_DIEM = LOCAL_HOST + "/lop-hoc-phan/sinh-vien/bang-diem/cap-nhat";
    public static final String API_POST_THEM_SINH_VIEN_VAO_LOP_HOC_PHAN = LOCAL_HOST + "/lop-hoc-phan/them/sinh-vien/";
    public static final String API_POST_XOA_SINH_VIEN_KHOI_LOP_HOC_PHAN = LOCAL_HOST + "/lop-hoc-phan/xoa/sinh-vien/";
    public static final String API_POST_TIM_KIEM_SINH_VIEN_TRONG_LHP = LOCAL_HOST + "/bang-diem/lop-hoc-phan/tim-kiem";
    public static final String API_POST_DANH_SACH_DIEM_CUA_LHP = LOCAL_HOST + "/lop-hoc-phan/danh-sach-bang-diem";
    public static final String API_POST_LHP_VA_SO_TIET_DIEM_DANH = LOCAL_HOST + "/giao-vien/lop-hocp-phan/so-tiet-diem-danh/{maLhp}";
    public static final String API_POST_CAP_NHAT_LHP_VA_SO_TIET_DIEM_DANH = LOCAL_HOST + "/giao-vien/lop-hocp-phan/cap-nhat/so-tiet-diem-danh";
    public static final String API_POST_TONG_TIET_SV_DIEM_DANH = LOCAL_HOST + "/mon-hoc/lop-hoc-phan/diem-danh/sinh-vien-voi-so-tiet-diem-danh";
    public static final String API_POST_TINH_DIEM_CC = LOCAL_HOST + "/bang-diem/lop-hoc-phan/tinh-diem-chuyen-can";
}
