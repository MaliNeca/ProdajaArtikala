-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: prodavnica
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
-- Table structure for table `rezervacija`
--

DROP TABLE IF EXISTS `rezervacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rezervacija` (
  `idRezervacija` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(64) DEFAULT NULL,
  `Prezime` varchar(64) DEFAULT NULL,
  `JMBG` varchar(13) NOT NULL,
  `Kolicina` int(11) DEFAULT NULL,
  `fk_idArtikal` int(11) NOT NULL,
  `fk_idProdavnica` int(11) NOT NULL,
  `Istek` varchar(30) DEFAULT NULL,
  `Kontakt` varchar(45) DEFAULT NULL,
  `MestoPodizanja` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRezervacija`),
  UNIQUE KEY `idRezervacija_UNIQUE` (`idRezervacija`),
  UNIQUE KEY `JMBG_UNIQUE` (`JMBG`),
  KEY `FK_rezervacija_fk_idArtikal` (`fk_idArtikal`),
  KEY `FK_rezervacija_fk_idProdavnica` (`fk_idProdavnica`),
  CONSTRAINT `FK_rezervacija_fk_idArtikal` FOREIGN KEY (`fk_idArtikal`) REFERENCES `artikal` (`idArtikal`),
  CONSTRAINT `FK_rezervacija_fk_idProdavnica` FOREIGN KEY (`fk_idProdavnica`) REFERENCES `prodavnica` (`idProdavnica`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervacija`
--

LOCK TABLES `rezervacija` WRITE;
/*!40000 ALTER TABLE `rezervacija` DISABLE KEYS */;
INSERT INTO `rezervacija` VALUES (35,'Nemanja','Djokic','0303994740086',1,9,2,'28-01-2018 11:44','','Gigatron 02'),(36,'Nemanja','Djokic','12345',7,9,2,'28-01-2018 11:45','0606106173','Gigatron 01'),(37,'213','213','312',7,9,2,'28-01-2018 11:48','123','Gigatron 01'),(38,'Nema','nja','das',7,9,2,'28-01-2018 12:02','','Gigatron 02');
/*!40000 ALTER TABLE `rezervacija` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-26 12:20:07
