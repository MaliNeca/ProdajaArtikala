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
-- Table structure for table `na_stanju`
--

DROP TABLE IF EXISTS `na_stanju`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `na_stanju` (
  `Kolicina` int(11) DEFAULT NULL,
  `idProdavnica` int(11) NOT NULL,
  `idArtikal` int(11) NOT NULL,
  PRIMARY KEY (`idProdavnica`,`idArtikal`),
  KEY `FK_na_stanju_idArtikal` (`idArtikal`),
  CONSTRAINT `FK_na_stanju_idArtikal` FOREIGN KEY (`idArtikal`) REFERENCES `artikal` (`idArtikal`),
  CONSTRAINT `FK_na_stanju_idProdavnica` FOREIGN KEY (`idProdavnica`) REFERENCES `prodavnica` (`idProdavnica`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `na_stanju`
--

LOCK TABLES `na_stanju` WRITE;
/*!40000 ALTER TABLE `na_stanju` DISABLE KEYS */;
INSERT INTO `na_stanju` VALUES (15,1,1),(3,1,2),(32,1,3),(109,1,4),(3,1,14),(2,1,15),(39,2,1),(29,2,2),(15,2,3),(29,2,4),(14,2,5),(11,2,9),(5,2,13),(35,3,2),(16,3,3),(9,3,4),(77,3,5),(1,3,6),(5,3,9),(30,4,1),(50,4,4);
/*!40000 ALTER TABLE `na_stanju` ENABLE KEYS */;
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
