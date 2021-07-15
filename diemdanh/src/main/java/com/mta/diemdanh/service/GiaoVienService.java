package com.mta.diemdanh.service;

import com.mta.diemdanh.entity.TblGiaoVien;

import java.util.Optional;

public interface GiaoVienService {
    Optional<TblGiaoVien> getGiaoVienById(String maGv);
}
