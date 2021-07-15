package com.mta.diemdanh.controller;

import com.mta.diemdanh.entity.TblLopHocPhan;
import com.mta.diemdanh.entity.TblSinhVien;
import com.mta.diemdanh.form.SearchStudentForm;
import com.mta.diemdanh.service.LopHocPhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LopHocPhanController {
    private LopHocPhanService service;

    @Autowired
    public LopHocPhanController(LopHocPhanService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping(value = "/tkb-sinh-vien/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<TblLopHocPhan>> getThoiKhoaBieuSinhVien(@RequestParam String id){
        List<TblLopHocPhan> list = service.getThoiKhoaBieuSinhVien(id);
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/tkb-giao-vien/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<TblLopHocPhan>> getThoiKhoaBieuGiaoVien(@RequestParam String id){
        List<TblLopHocPhan> list = service.getThoiKhoaBieuGiaoVien(id);
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/lop-hoc-phan/danh-sach-sinh-vien/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<TblSinhVien>> getDanhSsachSinhVienCuaLopHocPhan(@RequestParam String id){
        List<TblSinhVien> list = service.getDanhSachSinhVienCuaLopHocPhan(id);
        if(list == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
