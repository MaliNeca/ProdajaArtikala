-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: proizvodnja
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `artikal`
--

DROP TABLE IF EXISTS `artikal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artikal` (
  `idArtikal` int(11) NOT NULL AUTO_INCREMENT,
  `Cena` double DEFAULT NULL,
  `Naziv` varchar(45) DEFAULT NULL,
  `Tip` varchar(45) DEFAULT NULL,
  `Vreme` int(11) DEFAULT NULL,
  PRIMARY KEY (`idArtikal`),
  UNIQUE KEY `idArtikal_UNIQUE` (`idArtikal`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artikal`
--

LOCK TABLES `artikal` WRITE;
/*!40000 ALTER TABLE `artikal` DISABLE KEYS */;
INSERT INTO `artikal` VALUES (1,25990,'Inteli5','Procesor',1),(2,179990,'IntelI9','Procesor',2),(3,39990,'AMDRyzen','Procesor',3),(4,43990,'IntelI7','Procesor',4),(5,41990,'IntelI3','Procesor',5),(6,69990,'ASUS ','MaticnaPloca',6),(7,25990,'GIGABYTE','MaticnaPloca',7),(8,64990,'iPhone7','Telefon',8),(9,119990,'iPhoneX','Telefon',9),(10,89990,'iPhone8','Telefon',10),(11,49990,'iPhone6s','Telefon',1),(12,49990,'SamsungS6','Telefon',2),(13,59990,'SamsungS7','Telefon',3),(14,69990,'SamsungS8','Telefon',4),(15,79990,'SamsungS9','Telefon',5),(16,5990,'WD1TB','HardDisk',6),(17,5490,'Toshiba500GB','HardDisk',7),(18,63990,'SamsungSSD1TB','SSD',8),(19,84990,'WDSSD2TB','SSD',9);
/*!40000 ALTER TABLE `artikal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-26 12:20:08
