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
-- Table structure for table `promet`
--

DROP TABLE IF EXISTS `promet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promet` (
  `idPromet` int(11) NOT NULL AUTO_INCREMENT,
  `BrojProdatih` int(11) DEFAULT NULL,
  `Datum` varchar(15) DEFAULT NULL,
  `Iznos` double DEFAULT NULL,
  `fk_prodavnica` int(11) NOT NULL,
  PRIMARY KEY (`idPromet`),
  UNIQUE KEY `idPromet_UNIQUE` (`idPromet`),
  KEY `FK_promet_fk_prodavnica` (`fk_prodavnica`),
  CONSTRAINT `FK_promet_fk_prodavnica` FOREIGN KEY (`fk_prodavnica`) REFERENCES `prodavnica` (`idProdavnica`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promet`
--

LOCK TABLES `promet` WRITE;
/*!40000 ALTER TABLE `promet` DISABLE KEYS */;
INSERT INTO `promet` VALUES (4,1,'25-12-2017',100,1),(5,33,'26-12-2017',3300,1),(6,4,'26-12-2017',400,2),(7,7,'27-12-2017',700,1),(8,2,'28-12-2017',200,1),(9,4,'28-12-2017',400,2),(10,1,'03-01-2018',100,1),(11,15,'06-01-2018',6660,1),(12,1,'07-01-2018',123,1),(13,10,'08-01-2018',1230,1),(14,1,'08-01-2018',154,2),(15,30,'09-01-2018',18802,1),(16,2,'26-01-2018',87980,1);
/*!40000 ALTER TABLE `promet` ENABLE KEYS */;
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
