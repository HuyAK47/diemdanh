package com.mta.diemdanh.service;

import com.mta.diemdanh.entity.TblHocPhan;
import com.mta.diemdanh.entity.TblLopHocPhan;
import com.mta.diemdanh.entity.TblSinhVien;

import java.util.List;
import java.util.Optional;

public interface LopHocPhanService {
    List<TblLopHocPhan> getThoiKhoaBieuSinhVien(String id);
    List<TblLopHocPhan> getThoiKhoaBieuGiaoVien(String id);
    List<TblSinhVien> getDanhSachSinhVienCuaLopHocPhan(String id);
}
