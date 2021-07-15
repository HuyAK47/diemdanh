package com.mta.diemdanh.controller;

import com.mta.diemdanh.entity.TblLhpVaSoTietDiemDanh;
import com.mta.diemdanh.form.UpdateLhpAndStddForm;
import com.mta.diemdanh.service.LhpVaSoTietDiemDanhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class LhpVaStddController {
    private LhpVaSoTietDiemDanhService service;

    @Autowired
    public LhpVaStddController(LhpVaSoTietDiemDanhService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping(value = "/giao-vien/lop-hocp-phan/so-tiet-diem-danh/{maLhp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TblLhpVaSoTietDiemDanh getLHpVaStdd(@RequestParam String maLhp){
        return service.LhpVaSoTietDiemDanh(maLhp);
    }

    @ResponseBody
    @PostMapping(value = "/giao-vien/lop-hocp-phan/cap-nhat/so-tiet-diem-danh", produces = MediaType.APPLICATION_JSON_VALUE)
    public Float capNhatLHpVaStdd(@RequestBody UpdateLhpAndStddForm form){
        System.out.println(form.getMaLopHocPhan());
        return service.capNhatSoTietDiemDanhCuaLHP(form.getMaLopHocPhan(), form.getTongSoTietDiemDanh());
    }
}
