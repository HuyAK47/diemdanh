package com.mta.diemdanh.repository;

import com.mta.diemdanh.entity.TblTaiKhoan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TaiKhoanRepository extends CrudRepository<TblTaiKhoan, Integer> {
    @Query("SELECT tk FROM TblTaiKhoan tk where tk.tenDangNhap like ?1 and tk.matKhau like ?2")
    TblTaiKhoan getAccountByIdPass(String tenDangNhap, String matKhau);

    @Query("SELECT tk FROM TblTaiKhoan tk where tk.tenDangNhap like ?1")
    TblTaiKhoan getAccountById(String tenDangNhap);
}
