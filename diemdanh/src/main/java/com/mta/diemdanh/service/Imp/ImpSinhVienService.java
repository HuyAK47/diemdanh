package com.mta.diemdanh.service.Imp;

import com.mta.diemdanh.entity.TblSinhVien;
import com.mta.diemdanh.repository.SinhVienRepository;
import com.mta.diemdanh.service.SinhVienSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImpSinhVienService implements SinhVienSrvice {
    private SinhVienRepository repository;

    @Autowired
    public ImpSinhVienService(SinhVienRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<TblSinhVien> getSinhVienById(String id) {
        return repository.findById(id);
    }

    @Override
    public TblSinhVien getSinhVienByMaSV(String maSv) {
        return repository.getSinhVienByMaSV(maSv);
    }
}
