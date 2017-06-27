-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gradeprocess
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `itc521`
--

DROP TABLE IF EXISTS `itc521`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itc521` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `StudentID` int(11) NOT NULL,
  `StudentName` varchar(50) NOT NULL,
  `QMark` double NOT NULL,
  `Assignment1` double NOT NULL,
  `Assignment2` double NOT NULL,
  `Assignment3` double NOT NULL,
  `Exam` double NOT NULL,
  `Results` double NOT NULL,
  `Grade` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itc521`
--

LOCK TABLES `itc521` WRITE;
/*!40000 ALTER TABLE `itc521` DISABLE KEYS */;
INSERT INTO `itc521` VALUES (6,11111111,'Bikash Katwal',99,99,99,99,99,99,'HD'),(7,11111112,'Tom Cruise 1',87,98,95,87,65,79.25,'DI'),(8,10000111,'Angelina Jolie',99,66,88,88,68,73.05,'CR'),(9,12345678,'Susmita',88,7,55,55,98,70.95,'CR');
/*!40000 ALTER TABLE `itc521` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'gradeprocess'
--

--
-- Dumping routines for database 'gradeprocess'
--
/*!50003 DROP PROCEDURE IF EXISTS `createTable` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `createTable`(IN tableName varchar(50))
BEGIN
	SET @tableName=tableName;
    SET @createquery=CONCAT(' 
    CREATE TABLE IF NOT EXISTS ' , @tableName, ' (
            id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
            StudentID INT NOT NULL,
            StudentName VARCHAR(50) NOT NULL,
            QMark DOUBLE NOT NULL,
            Assignment1 DOUBLE NOT NULL,
            Assignment2 DOUBLE NOT NULL,
            Assignment3 DOUBLE NOT NULL,
            Exam DOUBLE NOT NULL,
            Results DOUBLE NOT NULL,
            Grade VARCHAR(50) NOT NULL,
            PRIMARY KEY (id)
        )
        
    ');
    
	PREPARE stmt FROM @createquery;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt; 
    SELECT TRUE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-07  0:02:49
