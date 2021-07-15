package com.mta.diemdanh.repository;

import com.mta.diemdanh.entity.TblLopHocPhan;
import com.mta.diemdanh.entity.TblSinhVien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LopHocPhanRepository extends CrudRepository<TblLopHocPhan, String> {

    @Query("select lhp from TblDiem diem, TblLopHocPhan lhp, TblHocPhan hp, TblHocKy hk " +
            "where diem.maSinhVien like ?1 " +
            "and diem.maLopHocPhan like lhp.maLopHocPhan " +
            "and lhp.maHocPhan like hp.maHocPhan " +
            "and lhp.maHocKy = hk.maHocKy and hk.trangThai = 1")
    List<TblLopHocPhan> getThoiKhoaBieuSinhVien(String masv);

    @Query("select lhp from TblGiangDay gd, TblLopHocPhan lhp, TblHocKy hk, TblHocPhan hp " +
            "where gd.maGiaoVien like ?1 " +
            "and gd.maLopHocPhan like lhp.maLopHocPhan " +
            "and lhp.maHocPhan like hp.maHocPhan " +
            "and lhp.maHocKy like hk.maHocKy " +
            "and hk.trangThai = 1 ")
    List<TblLopHocPhan> getThoiKhoaBieuGiaoVien(String id);

    @Query("select sv from TblLopHocPhan lhp, TblDiem d, TblSinhVien sv " +
            "where lhp.maLopHocPhan like ?1 " +
            "and lhp.maLopHocPhan like d.maLopHocPhan " +
            "and d.maSinhVien like sv.maSinhVien")
    List<TblSinhVien> getDanhSachSinhVienByMaLopHocPhan(String maLopHp);

    /*@Query("select sv from TblLopHocPhan lhp, TblDiem d, TblSinhVien sv " +
            "where lhp.maLopHocPhan like ?2 " +
            "and lhp.maLopHocPhan like d.maLopHocPhan " +
            "and d.maSinhVien like sv.maSinhVien " +
            "and d.maSinhVien like %?1%")
    List<TblSinhVien> timkiemSinhVienTrongLopHocPhan(String maSv, String maLopHp);*/
}
