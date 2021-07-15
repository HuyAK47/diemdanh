package com.mta.diemdanh.service.Imp;

import com.mta.diemdanh.entity.TblLhpVaSoTietDiemDanh;
import com.mta.diemdanh.repository.LhpVaSoTietDiemDanhRepository;
import com.mta.diemdanh.service.LhpVaSoTietDiemDanhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImpLhpVaSoBuoiDiemDanhService implements LhpVaSoTietDiemDanhService {
    private LhpVaSoTietDiemDanhRepository repository;

    @Autowired
    public ImpLhpVaSoBuoiDiemDanhService(LhpVaSoTietDiemDanhRepository repository) {
        this.repository = repository;
    }


    @Override
    public TblLhpVaSoTietDiemDanh LhpVaSoTietDiemDanh(String maLhp) {
        TblLhpVaSoTietDiemDanh entity = repository.getEntityByMaLHP(maLhp);
        return entity;
    }

    @Override
    public Float capNhatSoTietDiemDanhCuaLHP(String maLhp, Float soTDD) {
        TblLhpVaSoTietDiemDanh entity = repository.getEntityByMaLHP(maLhp);
        entity.setTongSoTietDiemDanh(soTDD + entity.getTongSoTietDiemDanh());
        repository.save(entity);
        return entity.getTongSoTietDiemDanh();
    }
}
