package com.mta.diemdanh.controller;

import com.mta.diemdanh.entity.TblDiemDanh;
import com.mta.diemdanh.form.MaSvMaHpFrom;
import com.mta.diemdanh.form.SoTietSinhVienDiemDanhForm;
import com.mta.diemdanh.service.DiemDanhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiemDanhController {
    private DiemDanhService service;

    @Autowired
    public DiemDanhController(DiemDanhService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping(value = "/sinh-vien/mon-hoc/ket-qua-diem-danh", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<TblDiemDanh> getDiemDanh(@RequestBody MaSvMaHpFrom info){
        return service.getListDiemDanhById(info.getMaSinhVien(), info.getMaHocPhan(), info.getMaLopHocPhan());
    }

    @ResponseBody
    @PostMapping(value = "/mon-hoc/lop-hoc-phan/thuc-hien-diem-danh", produces = MediaType.APPLICATION_JSON_VALUE)
    private TblDiemDanh diemDanh(@RequestBody TblDiemDanh diemDanh){
        return service.save(diemDanh);
    }

    @ResponseBody
    @PostMapping(value = "/mon-hoc/lop-hoc-phan/diem-danh/xoa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private void xoaBuoiDiemDanh(@RequestParam int id){
        service.xoaBuoiDiemDanh(id);
    }

    @ResponseBody
    @PostMapping(value = "/mon-hoc/lop-hoc-phan/diem-danh/sinh-vien-voi-so-tiet-diem-danh", produces = MediaType.APPLICATION_JSON_VALUE)
    private String tongSoTietDiemDanhCuaSinhVien(@RequestBody SoTietSinhVienDiemDanhForm form){
        return service.tongSoTietDiemDanhCuaSinhVien(form.getMaSinhVien(), form.getMaLopHocPhan());
    }
}
