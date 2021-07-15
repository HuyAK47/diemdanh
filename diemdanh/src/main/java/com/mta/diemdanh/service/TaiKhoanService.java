package com.mta.diemdanh.service;

import com.mta.diemdanh.entity.TblTaiKhoan;

public interface TaiKhoanService {
    TblTaiKhoan login(String taiKhoan, String matKhau);
    boolean changePassword(String taiKhoan, String matKhau, String matKhauMoi);
}
