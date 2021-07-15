package com.mta.diemdanh.service.Imp;

import com.mta.diemdanh.entity.TblDiemDanh;
import com.mta.diemdanh.entity.TblTaiKhoan;
import com.mta.diemdanh.repository.DiemDanhRepository;
import com.mta.diemdanh.repository.TaiKhoanRepository;
import com.mta.diemdanh.service.DiemDanhService;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class ImpDiemDanhService implements DiemDanhService {
    public final static String MAC_DEVICE_DEFAULT = "CHECK_IN_NORMAL_BY_TEACHER";
    private DiemDanhRepository diemDanhRepository;
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    public ImpDiemDanhService(DiemDanhRepository repository, TaiKhoanRepository taiKhoanRepository) {
        this.diemDanhRepository = repository;
        this.taiKhoanRepository = taiKhoanRepository;
    }

    @Override
    public List<TblDiemDanh> getListDiemDanhById(String masv, String maHp, String maLHP) {
        return diemDanhRepository.getListDiemDanhByIdSv(masv, maHp, maLHP);
    }

    @Override
    public TblDiemDanh save(TblDiemDanh diemDanh) {
        TblTaiKhoan taiKhoan = taiKhoanRepository.getAccountById(diemDanh.getMaSinhVien());
        String token = taiKhoan.getToken();
        if(!TextUtils.isEmpty(token)){
            byte[] decodedBytes = Base64.getDecoder().decode(token);
            String decodedToken = new String(decodedBytes);
            if(checkToken(decodedToken, diemDanh.getMaSinhVien())){
                return diemDanhRepository.save(diemDanh);
            }
        }
        if(diemDanh.getMacDevice().contains(MAC_DEVICE_DEFAULT)){
            return diemDanhRepository.save(diemDanh);
        }
        TblDiemDanh notDiemDanh = new TblDiemDanh();
        return notDiemDanh;
    }

    @Override
    public void xoaBuoiDiemDanh(int id) {
        diemDanhRepository.deleteById(id);
    }

    @Override
    public String tongSoTietDiemDanhCuaSinhVien(String maSv, String maLhp) {
        return diemDanhRepository.tongSoTietDiemDanhCuaSinhVien(maSv, maLhp);
    }

    private boolean checkToken(String token_acc_decode, String maSV){
        String sTime = token_acc_decode.replace("+:+"+maSV, "");
        long timeSession = Long.valueOf(sTime);
        long timeCurrent = System.currentTimeMillis();
        System.out.println(timeSession);
        System.out.println(timeCurrent);
        if(timeCurrent <= timeSession){
            return true;
        }
        return false;
    }

    private String convertTime(long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }
}
