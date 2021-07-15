-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: diem_danh
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tblbomon`
--

DROP TABLE IF EXISTS `tblbomon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tblbomon` (
  `ma_bo_mon` varchar(10) NOT NULL,
  `ten_bo_mon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ma_khoa` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ma_bo_mon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbomon`
--

LOCK TABLES `tblbomon` WRITE;
/*!40000 ALTER TABLE `tblbomon` DISABLE KEYS */;
INSERT INTO `tblbomon` VALUES ('mabm01','Bộ môn 01','makhoa01');
/*!40000 ALTER TABLE `tblbomon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbldiem`
--

DROP TABLE IF EXISTS `tbldiem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbldiem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ma_sinh_vien` varchar(10) DEFAULT NULL,
  `ma_lop_hoc_phan` varchar(10) DEFAULT NULL,
  `diem_chuyen_can` float DEFAULT NULL,
  `diem_thuong_xuyen` float DEFAULT NULL,
  `diem_thi` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbldiem`
--

LOCK TABLES `tbldiem` WRITE;
/*!40000 ALTER TABLE `tbldiem` DISABLE KEYS */;
INSERT INTO `tbldiem` VALUES (1,'masv01','malhp01',7,NULL,NULL),(2,'masv01','malhp02',7,NULL,NULL),(3,'masv01','malhp03',8,NULL,NULL),(4,'masv01','malhp04',8,NULL,NULL),(5,'masv01','malhp05',9,NULL,NULL),(6,'masv01','malhp06',8,NULL,NULL),(7,'masv01','malhp07',8,NULL,NULL),(8,'masv02','malhp08',5,NULL,NULL),(9,'masv01','malhp09',6,NULL,NULL),(10,'masv02','malhp10',2,8.5,NULL),(11,'masv01','malhp11',8,NULL,NULL),(12,'masv01','malhp12',7,NULL,NULL),(13,'masv01','malhp13',8,NULL,NULL),(14,'masv01','malhp10',7,8,7),(16,'masv02','malhp12',NULL,NULL,NULL),(17,'masv02','malhp13',NULL,NULL,NULL),(18,'masv01','malhp14',NULL,NULL,NULL),(19,'masv03','malhp10',6,8,9),(26,'masv02','malhp11',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tbldiem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbldiemdanh`
--

DROP TABLE IF EXISTS `tbldiemdanh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbldiemdanh` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ma_sinh_vien` varchar(10) DEFAULT NULL,
  `ma_lop_hoc_phan` varchar(10) DEFAULT NULL,
  `ngay_diem_danh` date DEFAULT NULL,
  `mac_device` varchar(100) DEFAULT NULL,
  `kieu_diem_danh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `kieu_tiet_hoc` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbldiemdanh`
--

LOCK TABLES `tbldiemdanh` WRITE;
/*!40000 ALTER TABLE `tbldiemdanh` DISABLE KEYS */;
INSERT INTO `tbldiemdanh` VALUES (1,'masv01','malhp10','2021-04-03','key mac default','Điểm danh 1 tiết','1'),(2,'masv02','malhp10','2021-04-03','key mac default','Điểm danh 1 tiết','1'),(3,'masv03','malhp10','2021-04-03','key mac default','Điểm danh 1 tiết','1'),(6,'masv01','malhp10','2021-04-03','B4:CE:F6:2F:DE:D4','Điểm danh 1 tiết','1'),(8,'masv01','malhp10','2021-04-17','key mac default','Không điểm danh','3'),(9,'masv03','malhp10','2021-04-17','key mac default','Không điểm danh','3'),(10,'masv01','malhp10','2021-04-18','B4:CE:F6:2F:DE:D4','Điểm danh 1 tiết','1'),(12,'masv03','malhp10','2021-04-18','key mac default','Điểm danh 1 tiết','1'),(13,'masv01','malhp13','2021-04-22','D4:D2:E5:00:20:46','Điểm danh đầu buổi','1'),(15,'masv01','malhp10','2021-05-29','B4:CE:F6:2F:DE:D4','Điểm danh 1 tiết','1'),(16,'masv02','malhp10','2021-05-29','key mac default','Điểm danh 1 tiết','1'),(17,'masv03','malhp10','2021-05-29','key mac default','Điểm danh 1 tiết','1'),(18,'masv01','malhp10','2021-06-16','key mac default','Điểm danh 1 tiết','2'),(19,'masv01','malhp10','2021-06-16','key mac default','Điểm danh 1 tiết','1'),(20,'masv01','malhp10','2021-06-18','key mac default','Điểm danh 1 tiết','1'),(21,'masv02','malhp10','2021-06-18','CHECK_IN_NORMAL_BY_TEACHER','Điểm danh 1 tiết','1'),(22,'masv03','malhp10','2021-06-18','CHECK_IN_NORMAL_BY_TEACHER','Điểm danh 1 tiết','1'),(23,'masv02','malhp10','2021-06-18','CHECK_IN_NORMAL_BY_TEACHER','Điểm danh 1 tiết','1'),(24,'masv03','malhp10','2021-06-18','CHECK_IN_NORMAL_BY_TEACHER','Điểm danh 1 tiết','2'),(25,'masv03','malhp10','2021-06-18','CHECK_IN_NORMAL_BY_TEACHER','Điểm danh 1 tiết','1'),(27,'masv01','malhp10','2021-06-20','B4:CE:F6:2F:DE:D4','Điểm danh đầu buổi','1.5'),(28,'masv02','malhp10','2021-06-20','CHECK_IN_NORMAL_BY_TEACHER','Điểm danh cả buổi','3'),(29,'masv02','malhp10','2021-06-20','CHECK_IN_NORMAL_BY_TEACHER','Điểm danh 1 tiết','1'),(30,'masv03','malhp10','2021-06-20','CHECK_IN_NORMAL_BY_TEACHER','Điểm danh 1 tiết','1'),(31,'masv03','malhp10','2021-06-20','CHECK_IN_NORMAL_BY_TEACHER','Điểm danh 1 tiết','1'),(32,'masv02','malhp10','2021-06-20','CHECK_IN_NORMAL_BY_TEACHER','Điểm danh 1 tiết','1'),(33,'masv01','malhp10','2021-06-20','B4:CE:F6:2F:DE:D4','Điểm danh 1 tiết','1.5'),(34,'masv03','malhp10','2021-06-20','CHECK_IN_NORMAL_BY_TEACHER','Điểm danh 1 tiết','1'),(35,'masv01','malhp10','2021-06-23','D4:D2:E5:FE:0A:7E','Điểm danh 1 tiết','1'),(36,'masv02','malhp10','2021-06-23','CHECK_IN_NORMAL_BY_TEACHER','Điểm danh 1 tiết','1');
/*!40000 ALTER TABLE `tbldiemdanh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblgiangday`
--

DROP TABLE IF EXISTS `tblgiangday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tblgiangday` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ma_giao_vien` varchar(10) DEFAULT NULL,
  `ma_lop_hoc_phan` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblgiangday`
--

LOCK TABLES `tblgiangday` WRITE;
/*!40000 ALTER TABLE `tblgiangday` DISABLE KEYS */;
INSERT INTO `tblgiangday` VALUES (1,'magv01','malhp01'),(2,'magv01','malhp10'),(3,'magv01','malhp11'),(4,'magv01','malhp12'),(5,'magv01','malhp13'),(6,'magv01','malhp14');
/*!40000 ALTER TABLE `tblgiangday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblgiaovien`
--

DROP TABLE IF EXISTS `tblgiaovien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tblgiaovien` (
  `ma_giao_vien` varchar(10) NOT NULL,
  `ten_giao_vien` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `hoc_ham` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `hoc_vi` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ma_khoa` varchar(10) DEFAULT NULL,
  `so_dien_thoai` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ma_giao_vien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblgiaovien`
--

LOCK TABLES `tblgiaovien` WRITE;
/*!40000 ALTER TABLE `tblgiaovien` DISABLE KEYS */;
INSERT INTO `tblgiaovien` VALUES ('magv01','Ten giao vien 01','tiến sĩ',' giáo sư','CNTT','0949438954','gv01@gmail.com');
/*!40000 ALTER TABLE `tblgiaovien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblhocky`
--

DROP TABLE IF EXISTS `tblhocky`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tblhocky` (
  `ma_hoc_ky` varchar(10) NOT NULL,
  `ten_hoc_ky` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `thoi_gian_bat_dau` date DEFAULT NULL,
  `thoi_gian_ket_thuc` date DEFAULT NULL,
  `trang_thai` int(11) DEFAULT NULL,
  PRIMARY KEY (`ma_hoc_ky`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblhocky`
--

LOCK TABLES `tblhocky` WRITE;
/*!40000 ALTER TABLE `tblhocky` DISABLE KEYS */;
INSERT INTO `tblhocky` VALUES ('2018201901','Học kỳ 1 năm học 2018 - 2019','2018-09-01','2018-12-30',0),('2018201902','Học kỳ 2 năm học 2018 - 2019','2019-01-15','2019-04-30',0),('2019202001','Học kỳ 1 năm học 2019 - 2020','2019-09-01','2019-12-30',0),('2019202002','Học kỳ 2 năm học 2019 - 2020','2020-01-15','2020-04-30',0),('2020202101','Học kỳ 1 năm học 2020 - 2021','2020-09-01','2020-12-30',0),('2020202102','Học kỳ 2 năm học 2020 - 2021','2021-01-15','2021-04-30',1);
/*!40000 ALTER TABLE `tblhocky` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblhocphan`
--

DROP TABLE IF EXISTS `tblhocphan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tblhocphan` (
  `ma_hoc_phan` varchar(10) NOT NULL,
  `ten_hoc_phan` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `so_tin_chi` int(11) DEFAULT NULL,
  PRIMARY KEY (`ma_hoc_phan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblhocphan`
--

LOCK TABLES `tblhocphan` WRITE;
/*!40000 ALTER TABLE `tblhocphan` DISABLE KEYS */;
INSERT INTO `tblhocphan` VALUES ('1234','Giai tich 1',4),('mahp01','Học phần 01',4),('mahp02','Học phần 02',4),('mahp03','Học phần 03',4),('mahp04','Học phần 04',4),('mahp05','Học phần 05',4),('mahp06','Học phần 06',4);
/*!40000 ALTER TABLE `tblhocphan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblkhoa`
--

DROP TABLE IF EXISTS `tblkhoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tblkhoa` (
  `ma_khoa` varchar(10) NOT NULL,
  `ten_khoa` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ma_khoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblkhoa`
--

LOCK TABLES `tblkhoa` WRITE;
/*!40000 ALTER TABLE `tblkhoa` DISABLE KEYS */;
INSERT INTO `tblkhoa` VALUES ('1111','Công nghệ thông tin'),('makhoa01','Khoa 01');
/*!40000 ALTER TABLE `tblkhoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblkhoahoc`
--

DROP TABLE IF EXISTS `tblkhoahoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tblkhoahoc` (
  `ma_khoa_hoc` varchar(10) NOT NULL,
  `ten_khoa_hoc` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `nam_bat_dau` date DEFAULT NULL,
  `nam_ket_thuc` date DEFAULT NULL,
  PRIMARY KEY (`ma_khoa_hoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblkhoahoc`
--

LOCK TABLES `tblkhoahoc` WRITE;
/*!40000 ALTER TABLE `tblkhoahoc` DISABLE KEYS */;
INSERT INTO `tblkhoahoc` VALUES ('k14','Khóa 14','2015-09-01','2020-06-01');
/*!40000 ALTER TABLE `tblkhoahoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbllopchuyennganh`
--

DROP TABLE IF EXISTS `tbllopchuyennganh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbllopchuyennganh` (
  `ma_lop_chuyen_nganh` varchar(10) NOT NULL,
  `ten_lop_chuyen_nganh` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ma_giao_vien` varchar(10) DEFAULT NULL,
  `ma_bo_mon` varchar(10) DEFAULT NULL,
  `ma_khoa_hoc` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ma_lop_chuyen_nganh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbllopchuyennganh`
--

LOCK TABLES `tbllopchuyennganh` WRITE;
/*!40000 ALTER TABLE `tbllopchuyennganh` DISABLE KEYS */;
INSERT INTO `tbllopchuyennganh` VALUES ('malcn01','Lớp chuyên ngành 01','magv01','mabm01','khoa01');
/*!40000 ALTER TABLE `tbllopchuyennganh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbllophocphan`
--

DROP TABLE IF EXISTS `tbllophocphan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbllophocphan` (
  `ma_lop_hoc_phan` varchar(10) NOT NULL,
  `ma_hoc_phan` varchar(10) DEFAULT NULL,
  `ma_hoc_ky` varchar(10) DEFAULT NULL,
  `si_so` int(11) DEFAULT NULL,
  `phong_hoc` varchar(10) DEFAULT NULL,
  `ngay_bat_dau` date DEFAULT NULL,
  `ngay_ket_thuc` date DEFAULT NULL,
  PRIMARY KEY (`ma_lop_hoc_phan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbllophocphan`
--

LOCK TABLES `tbllophocphan` WRITE;
/*!40000 ALTER TABLE `tbllophocphan` DISABLE KEYS */;
INSERT INTO `tbllophocphan` VALUES ('malhp01','mahp01','2020202101',50,'phonghoc01','2020-09-01','2020-12-15'),('malhp02','mahp02','2020202101',50,'phonghoc02','2020-09-01','2020-12-15'),('malhp03','mahp03','2020202101',50,'phonghoc03','2020-09-01','2020-12-15'),('malhp04','mahp04','2020202101',50,'phonghoc04','2020-09-01','2020-12-15'),('malhp05','mahp05','2020202101',50,'phonghoc05','2020-09-01','2020-12-15'),('malhp06','mahp06','2020202101',50,'phonghoc06','2020-09-01','2020-12-15'),('malhp07','mahp01','2020202102',50,'phonghoc01','2021-01-15','2021-04-30'),('malhp08','mahp01','2020202102',50,'phonghoc02','2021-01-15','2021-04-30'),('malhp09','mahp02','2020202102',50,'phonghoc02','2021-01-15','2021-04-30'),('malhp10','mahp02','2020202102',50,'phonghoc03','2021-01-15','2021-04-30'),('malhp11','mahp04','2020202102',30,'phonghoc05','2021-01-15','2021-04-30'),('malhp12','mahp05','2020202102',30,'phonghoc04','2021-01-15','2021-04-30'),('malhp13','mahp06','2020202102',35,'phonghoc03','2021-01-15','2021-04-30'),('malhp14','mahp06','2020202102',40,'phonghoc03','2021-01-15','2021-04-30');
/*!40000 ALTER TABLE `tbllophocphan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbllophocphanvasotietdiemdanh`
--

DROP TABLE IF EXISTS `tbllophocphanvasotietdiemdanh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbllophocphanvasotietdiemdanh` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ma_lop_hoc_phan` varchar(10) DEFAULT NULL,
  `tong_so_tiet_diem_danh` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbllophocphanvasotietdiemdanh`
--

LOCK TABLES `tbllophocphanvasotietdiemdanh` WRITE;
/*!40000 ALTER TABLE `tbllophocphanvasotietdiemdanh` DISABLE KEYS */;
INSERT INTO `tbllophocphanvasotietdiemdanh` VALUES (1,'malhp10',15),(2,'malhp11',0),(3,'malhp12',0),(4,'malhp13',1),(5,'malhp14',0);
/*!40000 ALTER TABLE `tbllophocphanvasotietdiemdanh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblquyen`
--

DROP TABLE IF EXISTS `tblquyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tblquyen` (
  `ma_quyen` varchar(10) NOT NULL,
  `ten_quyen` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ma_quyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblquyen`
--

LOCK TABLES `tblquyen` WRITE;
/*!40000 ALTER TABLE `tblquyen` DISABLE KEYS */;
INSERT INTO `tblquyen` VALUES ('gv','Giáo viên'),('sv','Sinh viên');
/*!40000 ALTER TABLE `tblquyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblsinhvien`
--

DROP TABLE IF EXISTS `tblsinhvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tblsinhvien` (
  `ma_sinh_vien` varchar(10) NOT NULL,
  `ten_sinh_vien` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `dan_toc` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `quoc_gia` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `nguyen_quan` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cho_o_hien_nay` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `so_dien_thoai` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `ma_lop_chuyen_nganh` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ma_sinh_vien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblsinhvien`
--

LOCK TABLES `tblsinhvien` WRITE;
/*!40000 ALTER TABLE `tblsinhvien` DISABLE KEYS */;
INSERT INTO `tblsinhvien` VALUES ('masv01','Sinh vien 01','1999-10-25','kinh','Việt nam','Bắc Ninh','Hà Nội','0949 499 699','sinhvien01@gmail.com','malcn01'),('masv02','Sinh vien 02','1998-10-25','kinh','Việt nam','Bắc Kạn','Hà Nội','0175 224 339','sinhvien02@gmail.com','malcn01'),('masv03','Sinh vien 03','1998-10-25','kinh','Việt nam','Hà Nam','Hà Nội','0175 224 668','sinhvien03@gmail.com','malcn01'),('masv04','Sinh vien 04','1998-10-25','kinh','Việt nam','Bắc Kạn','Hà Nội','0175 224 777','sinhvien04@gmail.com','malcn01');
/*!40000 ALTER TABLE `tblsinhvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbltaikhoan`
--

DROP TABLE IF EXISTS `tbltaikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbltaikhoan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten_dang_nhap` varchar(10) DEFAULT NULL,
  `mat_khau` varchar(30) DEFAULT NULL,
  `ma_quyen` varchar(10) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  `rooted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbltaikhoan`
--

LOCK TABLES `tbltaikhoan` WRITE;
/*!40000 ALTER TABLE `tbltaikhoan` DISABLE KEYS */;
INSERT INTO `tbltaikhoan` VALUES (1,'masv01','1','sv','MTYyNjMyNDU0NDE0MSs6K21hc3YwMQ==',NULL),(2,'masv02','1','sv',NULL,NULL),(3,'magv01','1','gv','MTYyNjMyNDUwMTA2MCs6K21hZ3YwMQ==',NULL),(4,'masv03','1','sv',NULL,NULL),(5,'masv04','1','sv',NULL,NULL);
/*!40000 ALTER TABLE `tbltaikhoan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-15 19:48:25
