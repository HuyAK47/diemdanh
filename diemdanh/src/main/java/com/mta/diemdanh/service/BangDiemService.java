package com.mta.diemdanh.service;

import com.mta.diemdanh.entity.TblDiem;

import java.util.List;

public interface BangDiemService {
    TblDiem getDiemMonHoc(String maSv, String maHP, String maLHP);

    TblDiem updateBangDiemForSinhVien(TblDiem bangDiem);

    void themMoiSinhVienVaoBangDiem(String maSv, String maLHP);

    void xoaMoiSinhVienKhoiBangDiem(String maSv, String maLHP);

    List<TblDiem> layDanhSachBangDiemCuaLopHocPhan(String maHP, String maLHP);

    List<TblDiem> timKiemSinhVienTrongLHP(String maHP, String maLHP, String maSV);

    Float tinhDiemChuyenCan(String maSv, String maLhp, Float diemCC);
}
