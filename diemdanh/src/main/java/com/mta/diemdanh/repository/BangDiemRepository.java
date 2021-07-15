package com.mta.diemdanh.repository;

import com.mta.diemdanh.entity.TblDiem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BangDiemRepository extends CrudRepository<TblDiem, Integer> {
    @Query("select d from TblHocPhan hp, TblDiem d, TblLopHocPhan lhp, TblHocKy hk " +
            "where d.maSinhVien like ?1 " +
            "and hp.maHocPhan like ?2 " +
            "and lhp.maLopHocPhan like ?3 " +
            "and hp.maHocPhan like lhp.maHocPhan " +
            "and lhp.maLopHocPhan like d.maLopHocPhan " +
            "and hk.maHocKy like lhp.maHocKy " +
            "and hk.trangThai = 1")
    TblDiem getDiemMonHoc(String maSv,String maHP, String maLHP);

    @Query("select d from TblDiem d where d.id = ?1")
    TblDiem getBangDiemBangId(int id);

    @Query("select d from TblHocPhan hp, TblDiem d, TblLopHocPhan lhp, TblHocKy hk " +
            "where hp.maHocPhan like ?1 " +
            "and lhp.maLopHocPhan like ?2 " +
            "and hp.maHocPhan like lhp.maHocPhan " +
            "and lhp.maLopHocPhan like d.maLopHocPhan " +
            "and hk.maHocKy like lhp.maHocKy " +
            "and hk.trangThai = 1")
    List<TblDiem> getDanhSachBangDiemCuaLopHocPhan(String maHP, String maLHP);

    @Query("select d from TblHocPhan hp, TblDiem d, TblLopHocPhan lhp, TblHocKy hk " +
            "where hp.maHocPhan like ?1 " +
            "and lhp.maLopHocPhan like ?2 " +
            "and hp.maHocPhan like lhp.maHocPhan " +
            "and lhp.maLopHocPhan like d.maLopHocPhan " +
            "and hk.maHocKy like lhp.maHocKy " +
            "and d.maSinhVien like %?3% " +
            "and hk.trangThai = 1")
    List<TblDiem> timKiemSinhVienTuBangDiemCuaLopHocPhan(String maHP, String maLHP, String maSv);

    @Query("select d from TblDiem d where d.maSinhVien like ?1 " +
            "and d.maLopHocPhan like ?2 ")
    TblDiem layBangDiemBangMaSvVaMaLhp(String maSv, String maLhp);

    @Query("select d.diemChuyenCan from TblDiem d where d.maSinhVien like ?1 " +
            "and d.maLopHocPhan like ?2 ")
    Float tinhDiemCC(String maSv, String maLhp);
}
