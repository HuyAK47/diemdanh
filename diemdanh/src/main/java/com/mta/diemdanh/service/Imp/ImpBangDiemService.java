package com.mta.diemdanh.service.Imp;

import com.mta.diemdanh.entity.TblDiem;
import com.mta.diemdanh.repository.BangDiemRepository;
import com.mta.diemdanh.service.BangDiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpBangDiemService implements BangDiemService {
    private BangDiemRepository repository;

    @Autowired
    public ImpBangDiemService(BangDiemRepository repository) {
        this.repository = repository;
    }

    @Override
    public TblDiem getDiemMonHoc(String maSv, String maHP, String maLHP) {
        return repository.getDiemMonHoc(maSv, maHP, maLHP);
    }

    @Override
    public TblDiem updateBangDiemForSinhVien(TblDiem bangDiem) {
        TblDiem diem = repository.getBangDiemBangId(bangDiem.getId());
        diem.setDiemChuyenCan(bangDiem.getDiemChuyenCan());
        diem.setDiemThuongXuyen(bangDiem.getDiemThuongXuyen());
        diem.setDiemThi(bangDiem.getDiemThi());
        return repository.save(diem);
    }

    @Override
    public void themMoiSinhVienVaoBangDiem(String maSv, String maLHP) {
        TblDiem diem = new TblDiem();
        diem.setMaSinhVien(maSv);
        diem.setMaLopHocPhan(maLHP);
        repository.save(diem);
    }

    @Override
    public void xoaMoiSinhVienKhoiBangDiem(String maSv, String maLHP) {
        TblDiem diem = repository.layBangDiemBangMaSvVaMaLhp(maSv, maLHP);
        repository.delete(diem);
    }

    @Override
    public List<TblDiem> layDanhSachBangDiemCuaLopHocPhan(String maHP, String maLHP) {
        return repository.getDanhSachBangDiemCuaLopHocPhan(maHP, maLHP);
    }

    @Override
    public List<TblDiem> timKiemSinhVienTrongLHP(String maHP, String maLHP, String maSV) {
        return repository.timKiemSinhVienTuBangDiemCuaLopHocPhan(maHP, maLHP, maSV);
    }

    @Override
    public Float tinhDiemChuyenCan(String maSv, String maLhp, Float diemCC) {
        TblDiem diem = repository.layBangDiemBangMaSvVaMaLhp(maSv, maLhp);
        diem.setDiemChuyenCan(diemCC);
        repository.save(diem);
        return repository.tinhDiemCC(maSv, maLhp);
    }
}
