package com.mta.diemdanh.controller;

import com.mta.diemdanh.entity.TblGiaoVien;
import com.mta.diemdanh.service.GiaoVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GiaoVienController {
    private GiaoVienService service;

    @Autowired
    public GiaoVienController(GiaoVienService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping(value = "/giao-vien/{id}", name = MediaType.APPLICATION_JSON_VALUE)
    private Optional<TblGiaoVien> getGiaoVienById(@RequestParam String id){
        return service.getGiaoVienById(id);
    }
}
