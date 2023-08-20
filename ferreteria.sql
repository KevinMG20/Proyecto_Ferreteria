-- MariaDB dump 10.19  Distrib 10.4.24-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: ferreteria
-- ------------------------------------------------------
-- Server version	10.4.24-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleados` (
  `idEmpleado` varchar(5) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `puesto` varchar(35) NOT NULL,
  `turno` varchar(15) NOT NULL,
  PRIMARY KEY (`idEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES ('AL001','Ana Laura','Vendedor','Vespertino'),('AL002','Alan Lorenzo','Limpieza','Vespertino'),('EM001','Emmanuel Montes','Vendedor','Matutino'),('FR001','Felipe Ruiz Martinez','Administrador','Matutino'),('GM001','Guillermo Martinez','Administrador','Vespertino'),('JL001','José Luis Ortiz','Diseñador','Matutino'),('KG001','Kevin M. Garcia','Gerente','Matutino'),('ML001','Mauricio Lopez A.','Vendedor','Vespertino'),('RQ001','Ramiro Quiñones','Vendedor','Vespertino'),('RV001','Ramon Valdez','Administrador','Matutino'),('TR001','Tesy Rodriguez','Diseñador','Matutino');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entregas`
--

DROP TABLE IF EXISTS `entregas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entregas` (
  `folioEntrega` varchar(18) NOT NULL,
  `fecha` varchar(32) NOT NULL,
  `idProveedor` varchar(6) NOT NULL,
  `idEmpleado` varchar(5) NOT NULL,
  PRIMARY KEY (`folioEntrega`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entregas`
--

LOCK TABLES `entregas` WRITE;
/*!40000 ALTER TABLE `entregas` DISABLE KEYS */;
INSERT INTO `entregas` VALUES ('03NOV22_MAF001_01','3 nov. 2022','MAF001','EM001'),('NOV26_22_GRU001_01','27 nov. 2022','DIC001','AL001');
/*!40000 ALTER TABLE `entregas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `idProducto` varchar(6) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `precio` float NOT NULL,
  `existencias` int(11) NOT NULL,
  PRIMARY KEY (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES ('ESC001','Escalera tipo Tijera','Escaleras','SCALA',997,4),('ESM001','Esmeriladora 4-1/2','Electricos','Truper',1246,3),('FLE001','Flexómetro Contra Impacto','Medicion','Truper',103,10),('GUA001','Guantes Anticorte Niv 8','Equipamento','DERMACARE',120,4),('LIN001','Linterna de Aluminio Negra','Miscelaneos','Truper',254,9),('LLA001','Llave Stillson 24\'\'','Herramientas','RIDGID',1750,5),('MAR001','Martillo Pulido de Uña Recta','Herramientas','Truper',143,12),('NAV001','Navaja Metálica 6\'','Corte','Truper',62,10),('NAV002','Navaja abatible con puntas','Corte','Urrea',335,7),('TOR001','Tornillos para Tablaroca 1 1/8','Accesorios','Hillman',1.5,100);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productosdeentrega`
--

DROP TABLE IF EXISTS `productosdeentrega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productosdeentrega` (
  `folioEntrega` varchar(18) NOT NULL,
  `idProducto` varchar(6) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productosdeentrega`
--

LOCK TABLES `productosdeentrega` WRITE;
/*!40000 ALTER TABLE `productosdeentrega` DISABLE KEYS */;
INSERT INTO `productosdeentrega` VALUES ('NOV26_22_GRU001_01','FLE001',13),('NOV26_22_GRU001_01','NAV001',15),('03NOV22_MAF001_01','GUA001',7),('03NOV22_MAF001_01','LLA001',4),('03NOV22_MAF001_01','ESM001',5),('03NOV22_MAF001_01','MAR001',5);
/*!40000 ALTER TABLE `productosdeentrega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productosdeventa`
--

DROP TABLE IF EXISTS `productosdeventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productosdeventa` (
  `folioVenta` varchar(15) NOT NULL,
  `idProducto` varchar(6) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productosdeventa`
--

LOCK TABLES `productosdeventa` WRITE;
/*!40000 ALTER TABLE `productosdeventa` DISABLE KEYS */;
INSERT INTO `productosdeventa` VALUES ('VNT_27NOV22_001','ESC001',1),('VNT_27NOV22_002','GUA001',2),('VNT_27NOV22_002','NAV001',3),('VNT_27NOV22_002','TOR001',30);
/*!40000 ALTER TABLE `productosdeventa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedores` (
  `idProveedor` varchar(6) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `rfc` varchar(13) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  PRIMARY KEY (`idProveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES ('DIC001','DICOMMEX','DCMX472541JGN','3324100676'),('GRU001','Grupo AFT','AFTX845712DGF','5548319473'),('MAF001','Mafensa','MFSA987927ERT','8717184020'),('PRO001','Proveedor Ferretero de GDL','PFGD846242LKJ','3315780535');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventas` (
  `folioVenta` varchar(15) NOT NULL,
  `importe` float NOT NULL,
  `idEmpleado` varchar(5) NOT NULL,
  PRIMARY KEY (`folioVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES ('VNT_27NOV22_001',997,'AL001'),('VNT_27NOV22_002',472.92,'EM001');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-29 20:07:00
