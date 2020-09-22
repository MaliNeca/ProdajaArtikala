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
-- Table structure for table `prodavnica`
--

DROP TABLE IF EXISTS `prodavnica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prodavnica` (
  `idProdavnica` int(11) NOT NULL AUTO_INCREMENT,
  `Mesto` varchar(32) DEFAULT NULL,
  `Naziv` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`idProdavnica`),
  UNIQUE KEY `idProdavnica_UNIQUE` (`idProdavnica`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodavnica`
--

LOCK TABLES `prodavnica` WRITE;
/*!40000 ALTER TABLE `prodavnica` DISABLE KEYS */;
INSERT INTO `prodavnica` VALUES (1,'Kirovljeva 17','Gigatron 01'),(2,'Cara Nikolaja II 41','Gigatron 02'),(3,'Žička 18-20','Gigatron 03'),(4,'Ušće SC','Gigatron 04'),(5,'Bulevar kralja Aleksandra 196','Gigatron 05'),(6,'Ušće SC','Gigatron 06'),(7,'Immo centar','Gigatron 07'),(8,'Stadion SC','Gigatron 08'),(9,'Zira SC','Gigatron 09'),(10,'SC Karaburma','Gigatron 10');
/*!40000 ALTER TABLE `prodavnica` ENABLE KEYS */;
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
