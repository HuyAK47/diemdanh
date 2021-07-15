package com.mta.diemdanh.service.Imp;

import com.mta.diemdanh.entity.TblGiaoVien;
import com.mta.diemdanh.repository.GiaoVienRepository;
import com.mta.diemdanh.service.GiaoVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImpGiaoVienService implements GiaoVienService {
    private GiaoVienRepository repository;

    @Autowired
    public ImpGiaoVienService(GiaoVienRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<TblGiaoVien> getGiaoVienById(String maGv) {
        return repository.findById(maGv);
    }
}
