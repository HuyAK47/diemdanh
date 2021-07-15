package com.mta.diemdanh.repository;

import com.mta.diemdanh.entity.TblLhpVaSoTietDiemDanh;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LhpVaSoTietDiemDanhRepository extends CrudRepository<TblLhpVaSoTietDiemDanh, String> {

    // MTA HuyNQn thuc hien lay ra doi tuong LhpVaSbdd tu ma lop hoc phan
    @Query("select tbl from TblLhpVaSoTietDiemDanh tbl where tbl.maLopHocPhan like ?1")
    TblLhpVaSoTietDiemDanh getEntityByMaLHP(String maLhp);
}
