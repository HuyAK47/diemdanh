package com.mta.diemdanh.service.Imp;

import com.mta.diemdanh.entity.TblHocPhan;
import com.mta.diemdanh.entity.TblLopHocPhan;
import com.mta.diemdanh.entity.TblSinhVien;
import com.mta.diemdanh.repository.LopHocPhanRepository;
import com.mta.diemdanh.service.LopHocPhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImpLopHocPhanService implements LopHocPhanService {
    private LopHocPhanRepository repository;

    @Autowired
    public ImpLopHocPhanService(LopHocPhanRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TblLopHocPhan> getThoiKhoaBieuSinhVien(String id) {
        return repository.getThoiKhoaBieuSinhVien(id);
    }

    @Override
    public List<TblLopHocPhan> getThoiKhoaBieuGiaoVien(String id) {
        return repository.getThoiKhoaBieuGiaoVien(id);
    }

    @Override
    public List<TblSinhVien> getDanhSachSinhVienCuaLopHocPhan(String id) {
        return repository.getDanhSachSinhVienByMaLopHocPhan(id);
    }
}
