package com.mta.diemdanh.controller;

import com.mta.diemdanh.entity.TblTaiKhoan;
import com.mta.diemdanh.form.DangNhapForm;
import com.mta.diemdanh.form.DoiMatKhauForm;
import com.mta.diemdanh.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaiKhoanController {
    private TaiKhoanService service;

    @Autowired
    public TaiKhoanController(TaiKhoanService service) {
        this.service = service;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private TblTaiKhoan login(@RequestBody DangNhapForm dangNhap){
        TblTaiKhoan taiKhoan = service.login(dangNhap.getTenDangNhap(), dangNhap.getMatKhau());
        return taiKhoan;
    }

    @RequestMapping(value = "/taik-khoan/doi-mat-khau", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private boolean changePassword(@RequestBody DoiMatKhauForm form){
        return service.changePassword(form.getTenDangNhap(), form.getMatKhau(), form.getMatKhauMoi());
    }
}
