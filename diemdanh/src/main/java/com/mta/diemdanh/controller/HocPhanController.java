package com.mta.diemdanh.controller;

import com.mta.diemdanh.entity.TblHocPhan;
import com.mta.diemdanh.service.HocPhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HocPhanController {
    private HocPhanService service;

    @Autowired
    public HocPhanController(HocPhanService service) {
        this.service = service;
    }
}
