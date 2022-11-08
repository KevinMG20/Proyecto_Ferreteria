-- MariaDB dump 10.19  Distrib 10.4.24-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: escuela
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
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumnos` (
  `noRegistro` int(10) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(10) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apaterno` varchar(20) NOT NULL,
  `amaterno` varchar(20) NOT NULL,
  `nivel` varchar(20) NOT NULL,
  `grado` varchar(20) NOT NULL,
  `turno` varchar(10) NOT NULL,
  `dia` int(11) NOT NULL,
  `mes` varchar(20) NOT NULL,
  `year` int(11) NOT NULL,
  `direccion` varchar(60) NOT NULL,
  `calificacion` int(3) NOT NULL,
  `beca` varchar(20) NOT NULL,
  `taller` varchar(15) NOT NULL,
  `rfcPadre` varchar(13) NOT NULL,
  PRIMARY KEY (`matricula`),
  UNIQUE KEY `noRegistro` (`noRegistro`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
INSERT INTO `alumnos` VALUES (35,'PREP1BIS13','Isabel','Salas','Lorente','Preparatoria','Primer Semestre','Vespertino',19,'Diciembre',2005,'Aguila real #202- Col Aguilas',90,'Foraneo','Cocina','ROGV723571PP0'),(38,'PREP1BJC17','Juan Camilo','Meza','Ramirez','Preparatoria','Primer Semestre','Vespertino',2,'Julio',2013,'Las ninfas #18 - col Las palmas',96,'Excelencia','Mecanica','LULS362916GG1'),(14,'PREP2AMG20','Mariana','Gomez','Herrera','Preparatoria','Segundo Semestre','Matutino',2,'Septiembre',2010,'Napoles #201 - Col Haciendas',97,'Excelencia','Informatica','ROGV723571PP0'),(28,'PREP3ADC14','Daniel','Castrejon','Ortíz','Preparatoria','Tercer Semestre','Matutino',23,'Octubre',2006,'Olmos #505- Col Chapultepec',82,'Manutencion','Informatica','SAMA251421IO9'),(21,'PREP3ADR20','Daniela','Ruiz','Camarena','Preparatoria','Tercer Semestre','Matutino',12,'Junio',2004,'Juarez 293 - Revolucion',88,'Manutencion','Ofimatica','MAFT463821'),(39,'PREP3BCR19','Caleb','Rivera','Huerta','Preparatoria','Tercer Semestre','Vespertino',31,'Enero',2008,'San carlos #103 - Col Anahuac',91,'Excelencia','Electronica','LEGC362821D76'),(33,'PREP4BAA11','Alison','Arce','Gomez','Preparatoria','Cuarto Semestre','Vespertino',6,'Noviembre',2006,'Zafiro #312- Col Palma alta',96,'Excelencia','Informatica','CLTO251962L21'),(31,'PREP5AAM22','Álvaro','Murillo','Morte','Preparatoria','Quinto Semestre','Matutino',8,'Septiembre',2005,'Olivo #101- Col Del bosque',100,'Excelencia','Ofimatica','VASB422931U77'),(10,'PREP5BVC19','Victoria','Cuartas','Velandia','Preparatoria','Quinto Semestre','Vespertino',30,'Marzo',2005,'Av Amazonas #609 - Col Linda vista',95,'Excelencia','Ofimatica','ERMN286122KM2'),(18,'PREP6AEV20','Enrique','Vargas','Saenz','Preparatoria','Sexto Semestre','Matutino',24,'Septiembre',2004,'Lirios #140 - Col Quintas',99,'Excelencia','Cocina','VASB422931U77'),(1,'PREP6AVC18','Valentina','Cárdenas','Marín','Preparatoria','Sexto Semestre','Matutino',2,'Noviembre',2005,'Calle Argos #130 - Cielo Vista',93,'Foraneo','Cocina','MALU621927MMR'),(26,'PREP6BIB21','Irene','Blas','Martínez','Preparatoria','Sexto Semestre','Vespertino',31,'Enero',2005,'Galeana #130- Col San Diego ',90,'Foraneo','Mecanica','IRRD251936CX4'),(16,'PREP6BLC15','Leire','Costa','Cruz','Preparatoria','Sexto Semestre','Vespertino',15,'Enero',2005,'Jazmin #25 - Col Florida',80,'Manutencion','Musica','MECE302981NM4'),(84,'PRIM1AHM09','Mario Humberto','Medrano','Mora','Primaria','Primer Grado','Matutino',16,'Julio',2003,'Argos - Lima 201',70,'Manutencion','Ninguno','JULM382626JM8'),(34,'PRIM1MMS20','Mario','Suarez','Montero','Primaria','Primer Grado','Matutino',12,'Marzo',2014,'Perla #405- Col Maderera',89,'Manutencion','Ninguno','ALSR329261JMS'),(27,'PRIM2AIP10','Ismael','Palacios','Velazco','Primaria','Segundo Grado','Matutino',29,'Junio',2013,'Vergel #180- Col Valenzuela',89,'Foraneo','Ninguno','ERMN286122KM2'),(12,'PRIM2ARR18','Ramiro','Rocha','Pardo','Primaria','Segundo Grado','Matutino',19,'Octubre',2015,'Insurgentes #120 - Col Cumbres',82,'Foraneo','Ninguno','CEVG264491O01'),(17,'PRIM2AVM17','Vera','Mendoza','Pando','Primaria','Segundo Grado','Matutino',4,'Julio',2013,'Privada del campo #603',82,'Foraneo','Ninguno','ALSC736242AA3'),(19,'PRIM2BAC22','Alvaro','Cervantes','Suarez','Primaria','Segundo Grado','Vespertino',10,'Mayo',2013,'Gladiolas #209 - Col Cielo azul',73,'Manutencion','Ninguno','MAVM012392E12'),(22,'PRIM3ACC16','Carlos','Cuevas','Rubio','Primaria','Tercer Grado','Matutino',2,'Mayo',2012,'Mina #301- Col Roma',90,'Foraneo','Ninguno','LEGC362821D76'),(0,'PRIM3AKM16','Kevin Mark','Garcia','Garcia','Primaria','Tercer Grado','Matutino',17,'Noviembre',2005,'Andador Membrillo 101, Los Manzanos Canatlan, DGO',96,'Excelencia','Ninguno','GAJC690101GGC'),(36,'PRIM3BIG22','Ingrid','Gamboa','Claderón','Primaria','Tercer Grado','Vespertino',7,'Febrero',2012,'Arroyo #126- Col Miraflores',100,'Excelencia','Ninguno','DIPS173520YT5'),(32,'PRIM4BIO17','Itziar','Oliveira','Flores','Primaria','Cuarto Grado','Vespertino',30,'Octubre',2012,'Maguey #430- col Margaritas',80,'Manutencion','Ninguno','MALU621927MMR'),(4,'PRIM5ACL21','Carolina','Leal','Solís','Primaria','Quinto Grado','Matutino',12,'Marzo',2014,'Loma #303 - Col Parques',78,'Foraneo','Ninguno','ANSR732721BB6'),(24,'PRIM5AMF19','Macarena','Ferreiro','Castro','Primaria','Quinto Grado','Matutino',28,'Septiembre',2010,'Puebla #205- Col Perales',92,'Foraneo','Ninguno','ALCM019282JK0'),(13,'PRIM5BSL21','Sofía','Linares','Rincon','Primaria','Quinto Grado','Vespertino',28,'Agosto',2017,'Canelas #505 - Los alamos',70,'Manutencion','Ninguno','ALHC251427JH2'),(8,'PRIM6AJO20','Julio','Olmedo','Murquía','Primaria','Sexto Grado','Matutino',14,'Agosto',2016,'Av Juan carlos #85 - Col Villas',89,'Foraneo','Ninguno','FEHM219262YY2'),(9,'SECU1AAA21','Alicia','Alarcón','Cordoba','Secundaria','Primer Año','Matutino',24,'Septiembre',2012,'Santa maria #201 - Col Providencia',77,'Manutencion','Ninguno','IRRD251936CX4'),(3,'SECU1AAC22','Alicia','Cortés','Martinez','Secundaria','Primer Año','Matutino',23,'Abril',2009,'Austral #201 - Bella vista',88,'Manutencion','Ninguno','ALSR329261JMS'),(11,'SECU1AVO19','Veronica','Orozco','Marquez','Secundaria','Primer Año','Matutino',18,'Diciembre',2011,'Nazas #400 Col Lomas',99,'Excelencia','Ninguno','SAMA251421IO9'),(25,'SECU1BSV20','Saray','Vargas','Vasquez','Secundaria','Primer Año','Vespertino',4,'Agosto',2008,'Aldama #109- Col Bella vista',99,'Excelencia','Ninguno','FEHM219262YY2'),(29,'SECU2BAM17','Andrés','Molina','Garrido','Secundaria','Segundo Año','Vespertino',3,'Febrero',2009,'Nazas #301- Col Remedios',95,'Excelencia','Ninguno','CEVG264491O01'),(23,'SECU2BBG11','Bertha','García','Solano','Secundaria','Segundo Año','Vespertino',16,'Enero',2011,'Victoria #200- Col Progresista',87,'Manutencion','Ninguno','LULS362916GG1'),(37,'SECU3AIC15','Ivan','Camacho','Sanz','Secundaria','Tercer Año','Matutino',18,'Abril',2010,'Hidra #603- Col Cielo vista',88,'Manutencion','Ninguno','MECE302981NM4'),(15,'SECU3AJB16','Joel M','Bernal','Cervantes','Secundaria','Tercer Año','Matutino',9,'Febrero',2014,'Gardenia #905 - Las praderas',76,'Excelencia','Ninguno','DIPS173520YT5'),(30,'SECU3AZP13','Zaira','Perez','Millan','Secundaria','Tercer Año','Matutino',15,'Julio',2010,'Marmol #608- Col Loma dorada',99,'Excelencia','Ninguno','ALHC251427JH2'),(7,'SECU3BVC22','Vanessa','Cruz','Granados','Secundaria','Tercer Año','Vespertino',5,'Junio',2017,'Florida #115 - Col Santa Ana',98,'Excelencia','Ninguno','ALCM019282JK0');
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bajas`
--

DROP TABLE IF EXISTS `bajas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bajas` (
  `noRegistro` int(10) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(10) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apaterno` varchar(20) NOT NULL,
  `amaterno` varchar(20) NOT NULL,
  `nivel` varchar(20) NOT NULL,
  `grado` varchar(20) NOT NULL,
  `turno` varchar(10) NOT NULL,
  `dia` int(11) NOT NULL,
  `mes` varchar(20) NOT NULL,
  `year` int(11) NOT NULL,
  `direccion` varchar(60) NOT NULL,
  `calificacion` int(3) NOT NULL,
  `beca` varchar(20) NOT NULL,
  `taller` varchar(15) NOT NULL,
  `rfcPadre` varchar(13) NOT NULL,
  `tipoBaja` varchar(20) NOT NULL,
  PRIMARY KEY (`matricula`),
  UNIQUE KEY `noRegistro` (`noRegistro`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bajas`
--

LOCK TABLES `bajas` WRITE;
/*!40000 ALTER TABLE `bajas` DISABLE KEYS */;
INSERT INTO `bajas` VALUES (1,'PRIM1BJO06','Jose Luis','Ortiz','Rojo','Primaria','Primer Grado','Vespertino',19,'Agosto',2002,'H Ayuntamiento 903',90,'Excelencia','Musica','JLOI372612OPP','Permanente'),(3,'PRIM3BMG20','Miguel','Galindo','Sanchez','Primaria','Tercer Grado','Vespertino',4,'Febrero',2011,'Privada Paso real #504',84,'Manutencion','Ninguno','CLTO251962L21','Temporal');
/*!40000 ALTER TABLE `bajas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingresos`
--

DROP TABLE IF EXISTS `ingresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingresos` (
  `registro` int(11) NOT NULL,
  `ingresosPrim` int(11) NOT NULL,
  `numInsPrimaria` int(11) NOT NULL,
  `numInsPrimaria2` int(11) NOT NULL,
  `ingresosSecu` int(11) NOT NULL,
  `numInsSecundaria` int(11) NOT NULL,
  `ingresosPrepa` int(11) NOT NULL,
  `numInsPreparatoria` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  PRIMARY KEY (`registro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingresos`
--

LOCK TABLES `ingresos` WRITE;
/*!40000 ALTER TABLE `ingresos` DISABLE KEYS */;
INSERT INTO `ingresos` VALUES (0,25000,10,5,25000,10,39000,13,89000);
/*!40000 ALTER TABLE `ingresos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `padres`
--

DROP TABLE IF EXISTS `padres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `padres` (
  `rfc` varchar(13) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apaterno` varchar(20) NOT NULL,
  `amaterno` varchar(20) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  PRIMARY KEY (`rfc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `padres`
--

LOCK TABLES `padres` WRITE;
/*!40000 ALTER TABLE `padres` DISABLE KEYS */;
INSERT INTO `padres` VALUES ('ALCM019282JK0','Alvaro ','Cruz','Mendoza','6181076940\r'),('ALHC251427JH2','Alba ','Herrea','Cuellar','6189864263\r'),('ALSC736242AA3','Alfonso ','Sanchez','Carrera','6181224587\r'),('ALSR329261JMS','Alberto ','Suarez','Ruiz','6188856731\r'),('ANSR732721BB6','Antonio','Duran ','Ortiz','6181110967\r'),('CEVG264491O01','Celeste','Villamarin ','Galindo','6181320976\r'),('DIPS173520YT5','Diego ','Portilla','Solano','8161095401\r'),('ERMN286122KM2','Erika','Marquez','Nieto','6181252190\r'),('FEHM219262YY2','Fernando','Huerta','Mena','6181012139\r'),('GAJC690101GGC','Carmen','Garcia','Jimenez','6773667321'),('IRRD251936CX4','Irene ','Romer','Del rio ','6181252508\r'),('JULM382626JM8','Julieta','Morales','Higo','6774836263'),('LEGC362821D76','Leonel','Gonzalez','Carrazco','6181007423\r'),('LULS362916GG1','Lucia ','Leon','Santos','6181059044\r'),('MAFT463821','Mariela','Fuerte','Torres','6184736222'),('MALU621927MMR','Maria Luisa','Garcia','Perez','6183573225\r'),('MAVM012392E12','Marcela','Valencia','Mora','6181000827\r'),('MECE302981NM4','Melissa','Caceres','Eslava','6180987342\r'),('ROGV723571PP0','Rocio ','Galindo ','Villamizar','6187623487\r'),('SAMA251421IO9','Santiago','Macias','Aldaba','6181123409\r'),('VASB422931U77','Variel','Santos','Barragan','6181142112\r');
/*!40000 ALTER TABLE `padres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registroinscripciones`
--

DROP TABLE IF EXISTS `registroinscripciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registroinscripciones` (
  `noRegistro` int(11) NOT NULL AUTO_INCREMENT,
  `matriculaAlumno` varchar(10) NOT NULL,
  `nivel` varchar(20) NOT NULL,
  `grado` varchar(20) NOT NULL,
  `monto` int(11) NOT NULL,
  PRIMARY KEY (`noRegistro`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registroinscripciones`
--

LOCK TABLES `registroinscripciones` WRITE;
/*!40000 ALTER TABLE `registroinscripciones` DISABLE KEYS */;
INSERT INTO `registroinscripciones` VALUES (1,'PREP6AVC18','Preparatoria','Sexto Semestre',3000),(2,'PRIM3BMG20','Primaria','Tercer Grado',1500),(3,'SECU1AAC22','Secundaria','Primer Año',2500),(4,'PRIM5ACL21','Primaria','Quinto Grado',2000),(5,'SECU3BVC22','Secundaria','Tercer Año',2500),(6,'PRIM6AJO20','Primaria','Sexto Grado',2000),(7,'SECU1AAA21','Secundaria','Primer Año',2500),(8,'PREP5BVC19','Preparatoria','Quinto Semestre',3000),(9,'SECU1AVO19','Secundaria','Primer Año ',2500),(10,'PRIM2ARR18','Primaria','Segundo Grado',1500),(11,'PRIM5BSL21','Primaria','Quinto Grado ',2000),(12,'PREP2AMG20','Preparatoria','Segundo Semestre',3000),(13,'SECU3AJB16','Secundaria','Tercer Año',2500),(14,'PREP6BLC15','Preparatoria','Sexto Semestre',3000),(15,'PRIM2AVM17','Primaria','Segundo Grado',1500),(16,'PREP6AEV20','Preparatoria','Sexto Semestre',3000),(17,'PRIM2BAC22','Primaria','Segundo Grado',1500),(18,'SECU2BBG11','Secundaria','Segundo Año',2500),(19,'PRIM5AMF19','Primaria','Quinto Grado',2000),(20,'SECU1BSV20','Secundaria','Primer Año',2500),(21,'PREP6BIB21','Preparatoria','Sexto Semestre',3000),(22,'PRIM2AIP10','Primaria','Segundo Grado',1500),(23,'PREP3ADC14','Preparatoria','Tercer Semestre',3000),(24,'SECU2BAM17','Secundaria','Segundo Año',2500),(25,'SECU3AZP13','Secundaria','Tercer Año',2500),(26,'PREP5AAM22','Preparatoria','Quinto Semestre',3000),(27,'PRIM4BIO17','Primaria','Cuarto Grado',2000),(28,'PREP4BAA11','Preparatoria','Cuarto Semestre',3000),(29,'PRIM1MMS20','Primaria','Primer Grado',1500),(30,'PREP1BIS13','Preparatoria','Primer Semestre',3000),(31,'PRIM3BIG22','Primaria','Tercer Grado',1500),(32,'SECU3AIC15','Secundaria','Tercer Año',2500),(33,'PREP1BJC17','Preparatoria','Primer Semestre',3000),(34,'PREP3BCR19','Preparatoria','Tercer Semestre',3000),(35,'PRIM1BJO06','Primaria','Primer Grado',1500),(36,'PREP3ADR20','Preparatoria','Tercer Semestre',3000),(37,'PRIM3AKM16','Primaria','Tercer Grado',1500),(40,'PRIM1AHM09','Primaria','Primer Grado',1500);
/*!40000 ALTER TABLE `registroinscripciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-29 23:47:30
