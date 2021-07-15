package com.mta.diemdanh.service.Imp;

import com.mta.diemdanh.entity.TblHocPhan;
import com.mta.diemdanh.repository.HocPhanRepository;
import com.mta.diemdanh.service.HocPhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpHocPhanService implements HocPhanService {
    private HocPhanRepository repository;

    @Autowired
    public ImpHocPhanService(HocPhanRepository repository) {
        this.repository = repository;
    }
}
