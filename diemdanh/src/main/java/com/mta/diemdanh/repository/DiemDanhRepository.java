package com.mta.diemdanh.repository;

import com.mta.diemdanh.entity.TblDiem;
import com.mta.diemdanh.entity.TblDiemDanh;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiemDanhRepository extends CrudRepository<TblDiemDanh, Integer> {
    @Query("SELECT dd FROM TblDiemDanh dd, TblHocKy hk, TblLopHocPhan lhp, TblHocPhan hp " +
            "where dd.maSinhVien like ?1 " +
            "and dd.maLopHocPhan like  lhp.maLopHocPhan " +
            "and lhp.maHocPhan like hp.maHocPhan " +
            "and hp.maHocPhan like ?2 " +
            "and lhp.maLopHocPhan like ?3 " +
            "and lhp.maHocKy = hk.maHocKy " +
            "and hk.trangThai = 1 ")
    List<TblDiemDanh> getListDiemDanhByIdSv(String maSv, String maHp, String maLHP);

    @Query("SELECT SUM(dd.kieuTietHoc) FROM TblDiemDanh dd where dd.maSinhVien like ?1 " +
            "and dd.maLopHocPhan like ?2 ")
    String tongSoTietDiemDanhCuaSinhVien(String maSv, String maLhp);
}
