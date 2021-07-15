Create database DIEM_DANH;
USE DIEM_DANH;

create table tblhocphan(
ma_hoc_phan varchar(10) primary key,
ten_hoc_phan nvarchar(150),
so_tin_chi int
);

insert into tblhocphan(ma_hoc_phan, ten_hoc_phan, so_tin_chi) values ('1234','Giai tich 1', 4);
insert into tblhocphan(ma_hoc_phan, ten_hoc_phan, so_tin_chi) values ('mahp01','Học phần 01', 4);
insert into tblhocphan(ma_hoc_phan, ten_hoc_phan, so_tin_chi) values ('mahp02','Học phần 02', 4);
insert into tblhocphan(ma_hoc_phan, ten_hoc_phan, so_tin_chi) values ('mahp03','Học phần 03', 4);
insert into tblhocphan(ma_hoc_phan, ten_hoc_phan, so_tin_chi) values ('mahp04','Học phần 04', 4);
insert into tblhocphan(ma_hoc_phan, ten_hoc_phan, so_tin_chi) values ('mahp05','Học phần 05', 4);
insert into tblhocphan(ma_hoc_phan, ten_hoc_phan, so_tin_chi) values ('mahp06','Học phần 06', 4);

create table tblkhoa(
ma_khoa varchar(10) primary key,
ten_khoa nvarchar(150)
);

insert into tblkhoa(ma_khoa, ten_khoa) values ('makhoa01', 'Khoa 01');

create table tblkhoahoc(
ma_khoa_hoc varchar(10) primary key,
ten_khoa_hoc nvarchar(150),
nam_bat_dau date,
nam_ket_thuc date
);

insert into tblkhoahoc(ma_khoa_hoc, ten_khoa_hoc, nam_bat_dau, nam_ket_thuc) 
values ('khoa01', 'Khóa học 01', '2015-09-01', '2020-06-01');

create table tblhocky(
ma_hoc_ky varchar(10) primary key,
ten_hoc_ky nvarchar(150),
thoi_gian_bat_dau date,
thoi_gian_ket_thuc date,
trang_thai int
);

insert into tblhocky(ma_hoc_ky, ten_hoc_ky, thoi_gian_bat_dau, thoi_gian_ket_thuc,trang_thai) 
values ('2018201901', 'Học kỳ 1 năm học 2018 - 2019', '2018-09-01', '2018-12-30', 0);
insert into tblhocky(ma_hoc_ky, ten_hoc_ky, thoi_gian_bat_dau, thoi_gian_ket_thuc,trang_thai) 
values ('2018201902', 'Học kỳ 2 năm học 2018 - 2019', '2019-01-15', '2019-04-30', 0);
insert into tblhocky(ma_hoc_ky, ten_hoc_ky, thoi_gian_bat_dau, thoi_gian_ket_thuc,trang_thai) 
values ('2019202001', 'Học kỳ 1 năm học 2019 - 2020', '2019-09-01', '2019-12-30', 0);
insert into tblhocky(ma_hoc_ky, ten_hoc_ky, thoi_gian_bat_dau, thoi_gian_ket_thuc,trang_thai) 
values ('2019202002', 'Học kỳ 2 năm học 2019 - 2020', '2020-01-15', '2020-04-30', 0);
insert into tblhocky(ma_hoc_ky, ten_hoc_ky, thoi_gian_bat_dau, thoi_gian_ket_thuc,trang_thai) 
values ('2020202101', 'Học kỳ 1 năm học 2020 - 2021', '2020-09-01', '2020-12-30', 0);
insert into tblhocky(ma_hoc_ky, ten_hoc_ky, thoi_gian_bat_dau, thoi_gian_ket_thuc,trang_thai) 
values ('2020202102', 'Học kỳ 2 năm học 2020 - 2021', '2021-01-15', '2021-04-30', 1);

create table tblgiaovien(
ma_giao_vien varchar(10) primary key,
ten_giao_vien nvarchar(150),
hoc_ham nvarchar(100),
hoc_vi nvarchar(100),
ma_khoa varchar(10) references tblkhoa(ma_khoa),
so_dien_thoai varchar(15),
email varchar(50)
);

insert into tblgiaovien(ma_giao_vien, ten_giao_vien, hoc_ham, hoc_vi, ma_khoa, so_dien_thoai, email) 
values ('magv01', 'Ten giao vien 01', 'tiến sĩ', ' giáo sư', 'CNTT', '0949438954', 'gv01@gmail.com');

create table tbllophocphan(
ma_lop_hoc_phan varchar(10) primary key,
ma_hoc_phan varchar(10) references TblHocPhan(maHocPhan),
ma_hoc_ky varchar(10) references TbLHocKy(maHocKy),
si_so int,
phong_hoc varchar(10),
ngay_bat_dau date,
ngay_ket_thuc date
);

insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp01', 'mahp01', '2020202101', '50', 'phonghoc01', '2020-09-01', '2020-12-15');
insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp02', 'mahp02', '2020202101', '50', 'phonghoc02', '2020-09-01', '2020-12-15');
insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp03', 'mahp03', '2020202101', '50', 'phonghoc03', '2020-09-01', '2020-12-15');
insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp04', 'mahp04', '2020202101', '50', 'phonghoc04', '2020-09-01', '2020-12-15');
insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp05', 'mahp05', '2020202101', '50', 'phonghoc05', '2020-09-01', '2020-12-15');
insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp06', 'mahp06', '2020202101', '50', 'phonghoc06', '2020-09-01', '2020-12-15');

insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp07', 'mahp01', '2020202102', '50', 'phonghoc01', '2021-01-15', '2021-04-30');
insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp08', 'mahp01', '2020202102', '50', 'phonghoc02', '2021-01-15', '2021-04-30');
insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp09', 'mahp02', '2020202102', '50', 'phonghoc02', '2021-01-15', '2021-04-30');
insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp10', 'mahp02', '2020202102', '50', 'phonghoc03', '2021-01-15', '2021-04-30');
insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp11', 'mahp04', '2020202102', '30', 'phonghoc05', '2021-01-15', '2021-04-30');
insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp12', 'mahp05', '2020202102', '30', 'phonghoc0', '2021-01-15', '2021-04-30');
insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp13', 'mahp06', '2020202102', '35', 'phonghoc03', '2021-01-15', '2021-04-30');
insert into tbllophocphan(ma_lop_hoc_phan, ma_hoc_phan, ma_hoc_ky, si_so, phong_hoc, ngay_bat_dau, ngay_ket_thuc) 
values ('malhp14', 'mahp06', '2020202102', '40', 'phonghoc03', '2021-01-15', '2021-04-30');

create table tblLopHocPhanVaSoTietDiemDanh(
id int auto_increment primary key,
ma_lop_hoc_phan varchar(10) references tbllophocphan(ma_lop_hoc_phan),
tong_so_tiet_diem_danh float
);

create table tblgiangday(
id int auto_increment primary key,
ma_giao_vien varchar(10) references tblgiaovien(ma_giao_vien),
ma_lop_hoc_phan varchar(10) references tbllophocphan(ma_lop_hoc_phan)
);

insert into tblgiangday(id,ma_giao_vien, ma_lop_hoc_phan) 
values (0,'magv01', 'malhp01');
insert into tblgiangday(id,ma_giao_vien, ma_lop_hoc_phan) 
values (2,'magv01', 'malhp10');
insert into tblgiangday(id,ma_giao_vien, ma_lop_hoc_phan) 
values (3,'magv01', 'malhp11');
insert into tblgiangday(id,ma_giao_vien, ma_lop_hoc_phan) 
values (4,'magv01', 'malhp12');
insert into tblgiangday(id,ma_giao_vien, ma_lop_hoc_phan) 
values (5,'magv01', 'malhp13');
insert into tblgiangday(id,ma_giao_vien, ma_lop_hoc_phan) 
values (6,'magv01', 'malhp14');

create table tblbomon(
ma_bo_mon varchar(10) primary key,
ten_bo_mon nvarchar(100),
ma_khoa varchar(10) references tblkhoa(ma_khoa)
);

insert into tblbomon(ma_bo_mon, ten_bo_mon, ma_khoa) 
values ('mabm01', 'Bộ môn 01', 'makhoa01');

create table tbllopchuyennganh(
ma_lop_chuyen_nganh varchar(10) primary key,
ten_lop_chuyen_nganh nvarchar(150),
ma_giao_vien varchar(10) references tblgiaovien(ma_giao_vien),
ma_bo_mon varchar(10) references tblbomon(ma_bo_mon),
ma_khoa_hoc varchar(10) references tblkhoahoc(ma_khoa_hoc)
);

insert into tbllopchuyennganh(ma_lop_chuyen_nganh, ten_lop_chuyen_nganh, ma_giao_vien, ma_bo_mon, ma_khoa_hoc) 
values ('malcn01', 'Lớp chuyên ngành 01', 'magv01', 'mabm01', 'khoa01');

create table tblsinhvien(
ma_sinh_vien varchar(10) primary key,
ten_sinh_vien nvarchar(150),
ngay_sinh date,
dan_toc nvarchar(30),
quoc_gia nvarchar(30),
nguyen_quan nvarchar(50),
cho_o_hien_nay nvarchar(50),
so_dien_thoai varchar(15),
email varchar(50),
ma_lop_chuyen_nganh varchar(10) references tbllopchuyennganh(ma_lop_chuyen_nganh)
);

insert into tblsinhvien(ma_sinh_vien, ten_sinh_vien, ngay_sinh, dan_toc, quoc_gia, nguyen_quan,
 cho_o_hien_nay, so_dien_thoai, email, ma_lop_chuyen_nganh) 
values ('masv01', 'Sinh vien 01', '1999-10-25', 'kinh', 'Việt nam', 'Bắc Ninh', 'Hà Nội','0949 499 699',
'sinhvien01@gmail.com', 'malcn01');

insert into tblsinhvien(ma_sinh_vien, ten_sinh_vien, ngay_sinh, dan_toc, quoc_gia, nguyen_quan,
 cho_o_hien_nay, so_dien_thoai, email, ma_lop_chuyen_nganh) 
values ('masv02', 'Sinh vien 02', '1998-10-25', 'kinh', 'Việt nam', 'Bắc Kạn', 'Hà Nội','0175 224 339',
'sinhvien02@gmail.com', 'malcn01');

insert into tblsinhvien(ma_sinh_vien, ten_sinh_vien, ngay_sinh, dan_toc, quoc_gia, nguyen_quan,
 cho_o_hien_nay, so_dien_thoai, email, ma_lop_chuyen_nganh) 
values ('masv03', 'Sinh vien 03', '1998-10-25', 'kinh', 'Việt nam', 'Hà Nam', 'Hà Nội','0175 224 668',
'sinhvien03@gmail.com', 'malcn01');

insert into tblsinhvien(ma_sinh_vien, ten_sinh_vien, ngay_sinh, dan_toc, quoc_gia, nguyen_quan,
 cho_o_hien_nay, so_dien_thoai, email, ma_lop_chuyen_nganh) 
values ('masv04', 'Sinh vien 04', '1998-10-25', 'kinh', 'Việt nam', 'Bắc Kạn', 'Hà Nội','0175 224 777',
'sinhvien04@gmail.com', 'malcn01');

create table tbldiem(
id int auto_increment primary key,
ma_sinh_vien varchar(10) references tblsinhvien(ma_sinh_vien),
ma_lop_hoc_phan varchar(10) references tbllophocphan(ma_lop_hoc_phan),
diem_chuyen_can float,
diem_thuong_xuyen float,
diem_thi float
);

insert into tbldiem(id,ma_sinh_vien, ma_lop_hoc_phan, diem_chuyen_can, diem_thuong_xuyen, diem_thi) 
values (0,'masv01', 'malhp01', -1.0, -1.0, -1.0);
insert into tbldiem(id,ma_sinh_vien, ma_lop_hoc_phan, diem_chuyen_can, diem_thuong_xuyen, diem_thi) 
values (2,'masv01', 'malhp02', -1.0, -1.0, -1.0);
insert into tbldiem(id,ma_sinh_vien, ma_lop_hoc_phan, diem_chuyen_can, diem_thuong_xuyen, diem_thi) 
values (3,'masv01', 'malhp03', -1.0, -1.0, -1.0);
insert into tbldiem(id,ma_sinh_vien, ma_lop_hoc_phan, diem_chuyen_can, diem_thuong_xuyen, diem_thi) 
values (4,'masv01', 'malhp04', -1.0, -1.0, -1.0);
insert into tbldiem(id,ma_sinh_vien, ma_lop_hoc_phan, diem_chuyen_can, diem_thuong_xuyen, diem_thi) 
values (5,'masv01', 'malhp05', -1.0, -1.0, -1.0);
insert into tbldiem(id,ma_sinh_vien, ma_lop_hoc_phan, diem_chuyen_can, diem_thuong_xuyen, diem_thi) 
values (6,'masv01', 'malhp06', -1.0, -1.0, -1.0);
insert into tbldiem(id,ma_sinh_vien, ma_lop_hoc_phan, diem_chuyen_can, diem_thuong_xuyen, diem_thi) 
values (7,'masv01', 'malhp07', -1.0, -1.0, -1.0);
insert into tbldiem(id,ma_sinh_vien, ma_lop_hoc_phan, diem_chuyen_can, diem_thuong_xuyen, diem_thi) 
values (8,'masv02', 'malhp08', -1.0, -1.0, -1.0);
insert into tbldiem(id,ma_sinh_vien, ma_lop_hoc_phan, diem_chuyen_can, diem_thuong_xuyen, diem_thi) 
values (9,'masv01', 'malhp09', -1.0, -1.0, -1.0);
insert into tbldiem(id,ma_sinh_vien, ma_lop_hoc_phan, diem_chuyen_can, diem_thuong_xuyen, diem_thi) 
values (10,'masv02', 'malhp10', -1.0, -1.0, -1.0);
insert into tbldiem(id,ma_sinh_vien, ma_lop_hoc_phan, diem_chuyen_can, diem_thuong_xuyen, diem_thi) 
values (11,'masv01', 'malhp11', -1.0, -1.0, -1.0);
insert into tbldiem(id,ma_sinh_vien, ma_lop_hoc_phan, diem_chuyen_can, diem_thuong_xuyen, diem_thi) 
values (12,'masv01', 'malhp12', -1.0, -1.0, -1.0);
insert into tbldiem(id,ma_sinh_vien, ma_lop_hoc_phan, diem_chuyen_can, diem_thuong_xuyen, diem_thi) 
values (13,'masv01', 'malhp13', -1.0, -1.0, -1.0);

create table tbldiemdanh(
id int auto_increment primary key,
ma_sinh_vien varchar(10) references tblsinhvien(ma_sinh_vien),
ma_lop_hoc_phan varchar(10) references tbllophocphan(ma_lop_hoc_phan),
ngay_diem_danh date,
mac_device varchar(100),
kieu_diem_danh nvarchar(100),
kieu_tiet_hoc varchar(5)
);

insert into tbldiemdanh(id,ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (0,'masv01', 'malhp01', '2020-09-25', 'MAC-0123-0215-5550');

insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (8, 'masv01', 'malhp01', '2020-09-27', 'MAC-0123-0215-5550');

insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (2, 'masv01', 'malhp01', '2020-09-28', 'MAC-0123-0215-5550');

insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (3, 'masv01', 'malhp01', '2020-09-29', 'MAC-0123-0215-5550');

insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (13, 'masv01', 'malhp07', '2020-09-29', 'MAC-0123-0215-5550');

insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (9, 'masv01', 'malhp09', '2020-10-02', 'MAC-0123-0215-5550');

insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (10, 'masv01', 'malhp11', '2020-09-29', 'MAC-0123-0215-5550');

insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (11, 'masv01', 'malhp12', '2020-09-30', 'MAC-0123-0215-5550');

insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (12, 'masv01', 'malhp13', '2020-09-27', 'MAC-0123-0215-5550');

#sv 02
insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (4, 'masv02', 'malhp01', '2020-09-25', 'MAC-0123-0215-5558');

insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (5, 'masv02', 'malhp01', '2020-09-27', 'MAC-0123-0215-5558');

insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (6, 'masv02', 'malhp01', '2020-09-28', 'MAC-0123-0215-5558');

insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (7, 'masv02', 'malhp01', '2020-09-29', 'MAC-0123-0215-5558');

insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (14, 'masv02', 'malhp08', '2020-09-29', 'MAC-0123-0215-5558');

insert into tbldiemdanh(id, ma_sinh_vien, ma_lop_hoc_phan, ngay_diem_danh, mac_device) 
values (15, 'masv02', 'malhp10', '2020-10-12','MAC-0123-0215-5558');


create table tblquyen(
ma_quyen varchar(10) primary key,
ten_quyen nvarchar(30)
);

insert into tblquyen(ma_quyen, ten_quyen) values ('sv','Sinh viên');
insert into tblquyen(ma_quyen, ten_quyen) values ('gv','Giáo viên');

create table tbltaikhoan(
id int auto_increment primary key,
ten_dang_nhap varchar(10),
mat_khau varchar(30),
ma_quyen varchar(10) references tblquyen(ma_quyen)
);

insert into tbltaikhoan(id, ten_dang_nhap ,mat_khau, ma_quyen) values (1,'masv01','1','sv');
insert into tbltaikhoan(id, ten_dang_nhap ,mat_khau, ma_quyen) values (2,'masv02','1','sv');
insert into tbltaikhoan(id, ten_dang_nhap ,mat_khau, ma_quyen) values (4,'masv03','1','sv');
insert into tbltaikhoan(id, ten_dang_nhap ,mat_khau, ma_quyen) values (5,'masv04','1','sv');
insert into tbltaikhoan(id, ten_dang_nhap ,mat_khau, ma_quyen) values (3,'magv01','1','gv');
