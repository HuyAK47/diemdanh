package com.mta.diemdanh.repository;

import com.mta.diemdanh.entity.TblHocPhan;
import com.mta.diemdanh.entity.TblSinhVien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SinhVienRepository extends CrudRepository<TblSinhVien, String> {
    @Query("select sv from TblSinhVien sv where sv.maSinhVien like ?1 ")
    TblSinhVien getSinhVienByMaSV(String maSV);
}
