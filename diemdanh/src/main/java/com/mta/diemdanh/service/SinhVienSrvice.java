package com.mta.diemdanh.service;

import com.mta.diemdanh.entity.TblHocPhan;
import com.mta.diemdanh.entity.TblSinhVien;

import java.util.Optional;

public interface SinhVienSrvice {
    Optional<TblSinhVien> getSinhVienById(String id);
    TblSinhVien getSinhVienByMaSV(String maSv);
}
