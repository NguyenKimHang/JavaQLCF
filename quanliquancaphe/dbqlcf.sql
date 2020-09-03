-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: testbtlon
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ban`
--

DROP TABLE IF EXISTS `ban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ban` (
  `maBan` varchar(45) NOT NULL,
  `tenBan` varchar(45) DEFAULT NULL,
  `sucChua` int(11) DEFAULT NULL,
  `tinhTrangTrong` varchar(45) DEFAULT NULL,
  `viTri` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`maBan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ban`
--

LOCK TABLES `ban` WRITE;
/*!40000 ALTER TABLE `ban` DISABLE KEYS */;
INSERT INTO `ban` VALUES ('38937c3c-20b2-400e-a9c2-3a2f3cbebf6a','b44',4,'Đã đặt','lầu 1'),('d088bb44-8e9e-4dc9-8552-40a27dd01c09','b33',4,'Đã đặt','lầu 2'),('d306c4e0-98fb-44ce-ab31-503d251fb040','b77',9,'Đã đặt','trệt');
/*!40000 ALTER TABLE `ban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietdonhang`
--

DROP TABLE IF EXISTS `chitietdonhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietdonhang` (
  `hoadon_maHoaDon` varchar(45) NOT NULL,
  `maMon` varchar(45) NOT NULL,
  `tenMon` varchar(45) DEFAULT NULL,
  `donGia` double DEFAULT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `thanhTien` double DEFAULT NULL,
  KEY `fk_chitietdonhang_hoadon1_idx` (`hoadon_maHoaDon`),
  KEY `fk_chitietdonhang_maMon_idx` (`maMon`),
  CONSTRAINT `fk_chitietdonhang_maHoaDon` FOREIGN KEY (`hoadon_maHoaDon`) REFERENCES `hoadon` (`maHoaDon`),
  CONSTRAINT `fk_chitietdonhang_maMon` FOREIGN KEY (`maMon`) REFERENCES `mon` (`maMon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietdonhang`
--

LOCK TABLES `chitietdonhang` WRITE;
/*!40000 ALTER TABLE `chitietdonhang` DISABLE KEYS */;
INSERT INTO `chitietdonhang` VALUES ('cdb4dc18-232b-492d-8c0e-1722295c1dc7','8d6ec99b-6e1e-4387-91af-de9656fdd512','trà đá',2000,9,18000),('a1d934e3-8a52-40e6-a1f0-108b18d95aa1','9566f967-a74d-442d-9158-f3492e48af0f','trà đào',50000,1,50000),('9a60612e-3d60-42fd-8a77-f3a807f8370d','9566f967-a74d-442d-9158-f3492e48af0f','trà đào',50000,4,200000),('37fd0e15-d5bc-4626-b1fb-f4b03c95b79d','1fd0f5ee-be10-4ec4-b349-b0e3d8a3a095','olong hanh nhan',25000,1,25000),('c6dc95a3-e97e-4343-aa54-509f4b852440','9566f967-a74d-442d-9158-f3492e48af0f','trà đào',50000,8,400000),('1204ca9f-70be-4c8c-94c4-74cacf6e47b7','9566f967-a74d-442d-9158-f3492e48af0f','trà đào',50000,2,100000),('db805652-9db6-4ccd-a14f-bec21d1efdaf','1fd0f5ee-be10-4ec4-b349-b0e3d8a3a095','olong hanh nhan',25000,9,225000);
/*!40000 ALTER TABLE `chitietdonhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danhmuc`
--

DROP TABLE IF EXISTS `danhmuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danhmuc` (
  `maDM` int(11) NOT NULL AUTO_INCREMENT,
  `tenDM` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`maDM`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhmuc`
--

LOCK TABLES `danhmuc` WRITE;
/*!40000 ALTER TABLE `danhmuc` DISABLE KEYS */;
INSERT INTO `danhmuc` VALUES (1,'đồ ăn'),(2,'đồ uống');
/*!40000 ALTER TABLE `danhmuc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `maHoaDon` varchar(45) NOT NULL,
  `Ban_maBan` varchar(45) NOT NULL,
  `ngayDat` date DEFAULT NULL,
  `tongTien` double DEFAULT NULL,
  `ngayThanhToan` date DEFAULT NULL,
  PRIMARY KEY (`maHoaDon`),
  KEY `fk_DatBan_Ban1_idx` (`Ban_maBan`),
  CONSTRAINT `fk_DatBan_Ban1` FOREIGN KEY (`Ban_maBan`) REFERENCES `ban` (`maBan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES ('1204ca9f-70be-4c8c-94c4-74cacf6e47b7','d306c4e0-98fb-44ce-ab31-503d251fb040','2020-01-16',100000,'2020-01-03'),('37fd0e15-d5bc-4626-b1fb-f4b03c95b79d','38937c3c-20b2-400e-a9c2-3a2f3cbebf6a','2020-01-03',25000,'2020-01-03'),('3a36980a-01a0-4ac7-a8ba-c4fca83dec51','d088bb44-8e9e-4dc9-8552-40a27dd01c09','2020-01-03',175000,'2020-01-03'),('9a60612e-3d60-42fd-8a77-f3a807f8370d','d306c4e0-98fb-44ce-ab31-503d251fb040','2020-01-03',200000,'2020-01-03'),('a1d934e3-8a52-40e6-a1f0-108b18d95aa1','d306c4e0-98fb-44ce-ab31-503d251fb040','2020-01-03',50000,'2020-01-03'),('c6dc95a3-e97e-4343-aa54-509f4b852440','d306c4e0-98fb-44ce-ab31-503d251fb040','2020-01-01',400000,'2020-01-03'),('cdb4dc18-232b-492d-8c0e-1722295c1dc7','d088bb44-8e9e-4dc9-8552-40a27dd01c09','2020-01-03',18000,'2020-01-03'),('db805652-9db6-4ccd-a14f-bec21d1efdaf','d306c4e0-98fb-44ce-ab31-503d251fb040','2020-01-15',225000,'2020-01-04');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mon`
--

DROP TABLE IF EXISTS `mon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mon` (
  `maMon` varchar(45) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `giaBan` double DEFAULT NULL,
  `tinhTrangCon` varchar(45) DEFAULT NULL,
  `thoiDiemBan` varchar(45) DEFAULT NULL,
  `danhmuc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`maMon`),
  KEY `fk_mon_danhmucid_idx` (`danhmuc_id`),
  CONSTRAINT `fk_mon_danhmucid` FOREIGN KEY (`danhmuc_id`) REFERENCES `danhmuc` (`maDM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mon`
--

LOCK TABLES `mon` WRITE;
/*!40000 ALTER TABLE `mon` DISABLE KEYS */;
INSERT INTO `mon` VALUES ('1fd0f5ee-be10-4ec4-b349-b0e3d8a3a095','olong hanh nhan',25000,'Còn hàng','noel',2),('8d6ec99b-6e1e-4387-91af-de9656fdd512','trà đá',2000,'Còn hàng','thường',2),('9566f967-a74d-442d-9158-f3492e48af0f','trà đào',50000,'Còn hàng','noel',2);
/*!40000 ALTER TABLE `mon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `maNV` varchar(45) NOT NULL,
  `ten` varchar(45) DEFAULT NULL,
  `gioiTinh` varchar(45) DEFAULT NULL,
  `queQuan` varchar(45) DEFAULT NULL,
  `ngaySinh` date DEFAULT NULL,
  `tenTaiKhoan` varchar(45) NOT NULL,
  `matKhau` varchar(45) NOT NULL,
  PRIMARY KEY (`maNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('123@','kim thanh','nu','ninh thuan','1999-09-10','kthanh09','123456'),('33333','thanh','nu','ninh thuan','2000-10-09','thanh','11111');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-04 17:29:35
