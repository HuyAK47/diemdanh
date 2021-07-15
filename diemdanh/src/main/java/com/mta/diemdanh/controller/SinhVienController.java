package com.mta.diemdanh.controller;

import com.mta.diemdanh.entity.TblSinhVien;
import com.mta.diemdanh.form.MaSvMaHpFrom;
import com.mta.diemdanh.service.BangDiemService;
import com.mta.diemdanh.service.DiemDanhService;
import com.mta.diemdanh.service.LopHocPhanService;
import com.mta.diemdanh.service.SinhVienSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SinhVienController {
    private SinhVienSrvice sinhVienSrvice;
    private BangDiemService bangDiemService;
    private LopHocPhanService lopHocPhanService;

    /*@Autowired
        public SinhVienController(SinhVienSrvice srvice) {
            this.sinhVienSrvice = srvice;
        }*/
    @Autowired
    public SinhVienController(SinhVienSrvice sinhVienSrvice, BangDiemService bangDiemService, LopHocPhanService lopHocPhanService) {
        this.sinhVienSrvice = sinhVienSrvice;
        this.bangDiemService = bangDiemService;
        this.lopHocPhanService = lopHocPhanService;
    }

    @ResponseBody
    @PostMapping(value = "/sinh-vien/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<TblSinhVien> getSinhVienbyId(@RequestParam String id){
        return sinhVienSrvice.getSinhVienById(id);
    }

    @ResponseBody
    @PostMapping(value = "/lop-hoc-phan/them/sinh-vien/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String themSinhVienVaoLopHocPhan(@RequestBody MaSvMaHpFrom info){
        TblSinhVien sv = sinhVienSrvice.getSinhVienByMaSV(info.getMaSinhVien());
        List<TblSinhVien> listSv = lopHocPhanService.getDanhSachSinhVienCuaLopHocPhan(info.getMaLopHocPhan());
        if(sv != null && !listSv.contains(sv)){
            bangDiemService.themMoiSinhVienVaoBangDiem(info.getMaSinhVien(), info.getMaLopHocPhan());
            return "Đã thêm sinh viên "+info.getMaSinhVien() +" vào lớp học";
        }else if(listSv.contains(sv)){
            return "Sinh viên "+info.getMaSinhVien() +" đã có trong danh sách lớp học";
        }else {
            return "Sinh viên "+info.getMaSinhVien() +" không tồn tại trên hệ thống";
        }
    }

    @ResponseBody
    @PostMapping(value = "/lop-hoc-phan/xoa/sinh-vien/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String xoaSinhVienKhoiLopHocPhan(@RequestBody MaSvMaHpFrom info){
        TblSinhVien sv = sinhVienSrvice.getSinhVienByMaSV(info.getMaSinhVien());
        List<TblSinhVien> listSv = lopHocPhanService.getDanhSachSinhVienCuaLopHocPhan(info.getMaLopHocPhan());
        if(sv != null && listSv.contains(sv)){
            bangDiemService.xoaMoiSinhVienKhoiBangDiem(info.getMaSinhVien(), info.getMaLopHocPhan());
            return "Đã xóa sinh viên "+info.getMaSinhVien() +" khỏi lớp học";
        }else {
            return "Xóa sinh viên "+info.getMaSinhVien() +" thất bại";
        }
    }

}
