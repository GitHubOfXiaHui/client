-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: client
-- ------------------------------------------------------
-- Server version	5.7.12

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
-- Table structure for table `sec_permission`
--

DROP TABLE IF EXISTS `sec_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` tinyint(4) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `value` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modified` datetime DEFAULT NULL,
  `extension` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_eokfw9ti3d790vn14weeuwnb6` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sec_permission`
--

LOCK TABLES `sec_permission` WRITE;
/*!40000 ALTER TABLE `sec_permission` DISABLE KEYS */;
INSERT INTO `sec_permission` VALUES (1,1,'post:create','新建帖子',NULL,NULL,NULL),(2,2,'post:delete','删除帖子',NULL,NULL,NULL),(3,3,'post:update','修改帖子',NULL,NULL,NULL),(4,4,'post:view','查看帖子',NULL,NULL,NULL),(5,5,'user:create','新建用户',NULL,NULL,NULL),(6,6,'user:delete','删除用户',NULL,NULL,NULL),(7,7,'user:changePassword','修改密码',NULL,NULL,NULL),(8,8,'user:view','查看用户',NULL,NULL,NULL),(9,9,'user:role:update','设置角色',NULL,NULL,NULL),(10,10,'role:create','创建角色',NULL,NULL,NULL),(11,11,'role:delete','删除角色',NULL,NULL,NULL),(12,12,'role:privilege:update','设置权限',NULL,NULL,NULL),(13,13,'role:view','查看角色',NULL,NULL,NULL);
/*!40000 ALTER TABLE `sec_permission` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-14 15:50:15
