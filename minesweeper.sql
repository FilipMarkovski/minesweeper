/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.3.16-MariaDB : Database - minesweeper
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`minesweeper` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `minesweeper`;

/*Table structure for table `game` */

DROP TABLE IF EXISTS `game`;

CREATE TABLE `game` (
  `gameId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `userId` int(10) unsigned NOT NULL,
  `score` int(11) NOT NULL,
  `levelId` int(11) NOT NULL,
  PRIMARY KEY (`gameId`),
  KEY `userId` (`userId`),
  KEY `level` (`levelId`),
  CONSTRAINT `game_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `game_ibfk_2` FOREIGN KEY (`levelId`) REFERENCES `level` (`levelId`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4;

/*Data for the table `game` */

insert  into `game`(`gameId`,`date`,`userId`,`score`,`levelId`) values 
(113,'2020-08-22',34,116,1),
(114,'2020-08-22',34,9,1);

/*Table structure for table `level` */

DROP TABLE IF EXISTS `level`;

CREATE TABLE `level` (
  `levelId` int(11) NOT NULL,
  `difficulty` varchar(30) NOT NULL,
  `bombPercentage` double NOT NULL,
  PRIMARY KEY (`levelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `level` */

insert  into `level`(`levelId`,`difficulty`,`bombPercentage`) values 
(1,'Easy',0.1),
(2,'Normal',0.2),
(3,'Hard',0.35);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `highscore` int(11) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

insert  into `user`(`userId`,`username`,`password`,`email`,`highscore`) values 
(34,'pera','pera','pera@gmail.com',9),
(49,'markovski','sifra','filipmarkovski@gmail.com',10000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
