package com.mta.diemdanh.service;

import com.mta.diemdanh.entity.TblLhpVaSoTietDiemDanh;

import java.util.Optional;

public interface LhpVaSoTietDiemDanhService {
    TblLhpVaSoTietDiemDanh LhpVaSoTietDiemDanh(String maLhp);
    Float capNhatSoTietDiemDanhCuaLHP(String maLhp, Float soTDD);
}
