package com.mta.diemdanh.controller;

import com.mta.diemdanh.entity.TblDiem;
import com.mta.diemdanh.form.ListPointForm;
import com.mta.diemdanh.form.MaSvMaHpFrom;
import com.mta.diemdanh.form.SearchStudentForm;
import com.mta.diemdanh.form.TinhDiemCCForm;
import com.mta.diemdanh.service.BangDiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BangDiemController {
    private BangDiemService service;

    @Autowired
    public BangDiemController(BangDiemService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping(value = "/diem/for-sinh-vien", produces = MediaType.APPLICATION_JSON_VALUE)
    private TblDiem getBangDiemByIdForSv(@RequestBody MaSvMaHpFrom info) {
        return service.getDiemMonHoc(info.getMaSinhVien(), info.getMaHocPhan(), info.getMaLopHocPhan());
    }

    @ResponseBody
    @PostMapping(value = "/lop-hoc-phan/sinh-vien/bang-diem/cap-nhat", produces = MediaType.APPLICATION_JSON_VALUE)
    private TblDiem capNhatBangDiemChoSinhVien(@RequestBody TblDiem diem) {
        return service.updateBangDiemForSinhVien(diem);
    }

    @ResponseBody
    @PostMapping(value = "/lop-hoc-phan/danh-sach-bang-diem", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<TblDiem> layDanhSachBangDiemcuaLopHocPhan(@RequestBody ListPointForm form) {
        return service.layDanhSachBangDiemCuaLopHocPhan(form.getMaHocPhan(), form.getMaLopHocPhan());
    }

    @ResponseBody
    @PostMapping(value = "/bang-diem/lop-hoc-phan/tim-kiem", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<TblDiem> timKiemSinhVienTrongLHPTuBangDiem(@RequestBody SearchStudentForm form) {
        return service.timKiemSinhVienTrongLHP(form.getMaHocPhan(), form.getMaLopHocPhan(), form.getMaSinhVien());
    }

    @ResponseBody
    @PostMapping(value = "/bang-diem/lop-hoc-phan/tinh-diem-chuyen-can", produces = MediaType.APPLICATION_JSON_VALUE)
    private Float tinhDiemChuyenCan(@RequestBody TinhDiemCCForm form) {
        return service.tinhDiemChuyenCan(form.getMaSinhVien(), form.getMaLopHocPhan(), form.getDiemChuyenCan());
    }
}
