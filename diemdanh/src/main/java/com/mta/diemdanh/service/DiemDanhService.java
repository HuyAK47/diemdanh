package com.mta.diemdanh.service;

import com.mta.diemdanh.entity.TblDiemDanh;

import java.util.List;
import java.util.Optional;

public interface DiemDanhService {
     List<TblDiemDanh> getListDiemDanhById(String masv, String maHp, String maLHP);
     TblDiemDanh save(TblDiemDanh diemDanh);
     void xoaBuoiDiemDanh(int id);
     String tongSoTietDiemDanhCuaSinhVien(String maSv, String maLhp);
}
