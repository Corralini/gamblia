-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: gamblia
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `cartera`
--

DROP TABLE IF EXISTS `cartera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartera` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `SALDO` double NOT NULL DEFAULT '0',
  `TARJETA` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartera`
--

LOCK TABLES `cartera` WRITE;
/*!40000 ALTER TABLE `cartera` DISABLE KEYS */;
INSERT INTO `cartera` VALUES (1,2125.55,3454351);
/*!40000 ALTER TABLE `cartera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juego`
--

DROP TABLE IF EXISTS `juego`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `juego` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juego`
--

LOCK TABLES `juego` WRITE;
/*!40000 ALTER TABLE `juego` DISABLE KEYS */;
INSERT INTO `juego` VALUES (1,'poker'),(2,'blackjack'),(3,'slots');
/*!40000 ALTER TABLE `juego` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesa`
--

DROP TABLE IF EXISTS `mesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mesa` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(255) NOT NULL,
  `MAX` int NOT NULL DEFAULT '8',
  `ID_JUEGO` int NOT NULL,
  `PSSWD` varchar(255) DEFAULT NULL,
  `APUESTA_MIN` double NOT NULL,
  `CODE` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOMBRE_UNIQUE` (`NOMBRE`,`ID_JUEGO`) /*!80000 INVISIBLE */,
  UNIQUE KEY `mesa_CODE_uindex` (`CODE`),
  KEY `fk_MESA_JUEGO_idx` (`ID_JUEGO`),
  CONSTRAINT `fk_MESA_JUEGO` FOREIGN KEY (`ID_JUEGO`) REFERENCES `juego` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesa`
--

LOCK TABLES `mesa` WRITE;
/*!40000 ALTER TABLE `mesa` DISABLE KEYS */;
INSERT INTO `mesa` VALUES (1,'Poker pq.1 A',8,1,NULL,1,'0');
/*!40000 ALTER TABLE `mesa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimientos` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_CARTERA_OR` int NOT NULL,
  `ID_CARTERA_DEST` int DEFAULT NULL,
  `ASUNTO` varchar(255) NOT NULL,
  `CANTIDAD` double NOT NULL,
  `ID_OPERACION` int NOT NULL,
  `FECHA` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `fk_MOVIMIENTOS_BANCO1_idx` (`ID_CARTERA_OR`),
  KEY `fk_MOVIMIENTOS_BANCO2_idx` (`ID_CARTERA_DEST`),
  KEY `fk_MOVIMIENTOS_OPERACION1_idx` (`ID_OPERACION`),
  CONSTRAINT `fk_MOVIMIENTOS_BANCO1` FOREIGN KEY (`ID_CARTERA_OR`) REFERENCES `cartera` (`ID`),
  CONSTRAINT `fk_MOVIMIENTOS_BANCO2` FOREIGN KEY (`ID_CARTERA_DEST`) REFERENCES `cartera` (`ID`),
  CONSTRAINT `fk_MOVIMIENTOS_OPERACION1` FOREIGN KEY (`ID_OPERACION`) REFERENCES `operacion` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos`
--

LOCK TABLES `movimientos` WRITE;
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
INSERT INTO `movimientos` VALUES (1,1,NULL,'Ingreso efectivo',2000,1,'2021-01-10 20:00:53');
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operacion`
--

DROP TABLE IF EXISTS `operacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operacion` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `OPERACION` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacion`
--

LOCK TABLES `operacion` WRITE;
/*!40000 ALTER TABLE `operacion` DISABLE KEYS */;
INSERT INTO `operacion` VALUES (1,'Añadir'),(2,'Retirar');
/*!40000 ALTER TABLE `operacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ROL` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'root'),(2,'usuario'),(3,'croupier'),(4,'administrador');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(255) NOT NULL,
  `APELLIDO1` varchar(255) NOT NULL,
  `APELLIDO2` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PSSWD` varchar(255) NOT NULL,
  `ID_MESA` int DEFAULT NULL,
  `ID_ROL` int NOT NULL,
  `ID_CARTERA` int NOT NULL,
  `ACTIVE` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME_UNIQUE` (`USERNAME`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`),
  KEY `fk_USUARIO_MESA1_idx` (`ID_MESA`),
  KEY `fk_USUARIO_ROL1_idx` (`ID_ROL`),
  KEY `fk_USUARIO_BANCO1_idx` (`ID_CARTERA`),
  CONSTRAINT `fk_USUARIO_BANCO1` FOREIGN KEY (`ID_CARTERA`) REFERENCES `cartera` (`ID`),
  CONSTRAINT `fk_USUARIO_MESA1` FOREIGN KEY (`ID_MESA`) REFERENCES `mesa` (`ID`),
  CONSTRAINT `fk_USUARIO_ROL1` FOREIGN KEY (`ID_ROL`) REFERENCES `rol` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Alejandro Ricardo','Corral','Fernández','corral','acorralfdez@gmail.com','t1t42jGPYPIrnCH2O0Tc98rrnoTcx+gXIIblqU4Yj1RQPAgY9j2RNoehhyjaewD2',NULL,1,1,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-13  1:21:32
