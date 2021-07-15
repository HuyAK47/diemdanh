package com.mta.diemdanh.service.Imp;

import com.mta.diemdanh.entity.TblTaiKhoan;
import com.mta.diemdanh.repository.TaiKhoanRepository;
import com.mta.diemdanh.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Service
public class ImpTaiKhoanService implements TaiKhoanService {
    private static long TIME_SESSION = 600000; //mini second
    private TaiKhoanRepository repository;

    @Autowired
    public ImpTaiKhoanService(TaiKhoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public TblTaiKhoan login(String taiKhoan, String matKhau) {
        TblTaiKhoan tk = repository.getAccountByIdPass(taiKhoan, matKhau);
        if (tk != null) {
            long time = System.currentTimeMillis() + 600000;
            String date = time + "+:+" + taiKhoan;
            String encodedString = Base64.getEncoder().encodeToString(date.getBytes());
            tk.setToken(encodedString);
            repository.save(tk);
            return tk;
        } else {
            TblTaiKhoan loginFailed = new TblTaiKhoan();
            return loginFailed;
        }
    }

    @Override
    public boolean changePassword(String taiKhoan, String matKhau, String matKhauMoi) {
        TblTaiKhoan tk = repository.getAccountByIdPass(taiKhoan, matKhau);
        if (tk != null) {
            tk.setMatKhau(matKhauMoi);
            repository.save(tk);
            return true;
        }
        return false;
    }

    private String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }
}
