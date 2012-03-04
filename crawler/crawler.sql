# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.5.16
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2011-11-25 23:48:21
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for crawler
CREATE DATABASE IF NOT EXISTS `crawler` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `crawler`;


# Dumping structure for table crawler.relationship
CREATE TABLE IF NOT EXISTS `relationship` (
  `uid` varchar(50) NOT NULL,
  `friend_id` varchar(50) NOT NULL,
  PRIMARY KEY (`uid`,`friend_id`),
  KEY `FK_relationship_user_2` (`friend_id`),
  CONSTRAINT `FK_relationship_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_relationship_user_2` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table crawler.status
CREATE TABLE IF NOT EXISTS `status` (
  `id` varchar(50) NOT NULL,
  `uid` varchar(50) NOT NULL,
  `text` varchar(200) NOT NULL,
  `createdAt` date NOT NULL,
  `repostsCount` int(11) NOT NULL,
  `commentsCount` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_status_user` (`uid`),
  CONSTRAINT `FK_status_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table crawler.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(50) NOT NULL,
  `screenName` varchar(50) NOT NULL,
  `province` varchar(20) NOT NULL,
  `city` varchar(20) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `gender` varchar(10) NOT NULL,
  `followersCount` int(10) DEFAULT NULL,
  `friendsCount` int(10) DEFAULT NULL,
  `statusesCount` int(10) DEFAULT NULL,
  `createdAt` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
