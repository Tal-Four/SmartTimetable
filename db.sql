-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.20-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table smarttimetabledb.category
CREATE TABLE IF NOT EXISTS `category` (
  `CategoryID` smallint(6) unsigned NOT NULL,
  `UserID` smallint(6) unsigned NOT NULL,
  `Name` varchar(15) NOT NULL,
  `Modifier` float unsigned NOT NULL DEFAULT '1',
  `TasksCompleted` int(11) unsigned NOT NULL DEFAULT '0',
  `Colour` int(11) NOT NULL,
  `Hidden` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`CategoryID`,`UserID`),
  KEY `FK_category_user` (`UserID`),
  CONSTRAINT `FK_category_user` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table smarttimetabledb.category: ~8 rows (approximately)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
REPLACE INTO `category` (`CategoryID`, `UserID`, `Name`, `Modifier`, `TasksCompleted`, `Colour`, `Hidden`) VALUES
	(1, 2, 'a', 1, 0, 0, b'0'),
	(1, 3, 'd', 1, 0, 0, b'0'),
	(2, 2, 'b', 1, 0, -6749953, b'0'),
	(3, 2, 'c', 1, 0, 0, b'0'),
	(4, 2, 'e', 1, 0, -3407770, b'0'),
	(5, 2, 'green', 1.5, 0, -10027213, b'0'),
	(6, 2, 'l', 1, 0, 0, b'0'),
	(7, 2, 'updated', 1, 0, 0, b'0');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


-- Dumping structure for table smarttimetabledb.event
CREATE TABLE IF NOT EXISTS `event` (
  `UserID` smallint(6) unsigned NOT NULL,
  `EventID` smallint(6) unsigned NOT NULL,
  `EventName` varchar(20) NOT NULL,
  `Description` text,
  `Date` date DEFAULT NULL,
  `Day` int(11) unsigned DEFAULT NULL,
  `Colour` int(11) NOT NULL,
  `StartTime` float unsigned NOT NULL,
  `Hidden` bit(1) NOT NULL DEFAULT b'0',
  `EndTime` float unsigned NOT NULL,
  PRIMARY KEY (`EventID`,`UserID`),
  KEY `FK_UserID3_idx` (`UserID`),
  CONSTRAINT `FK_event_user` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table smarttimetabledb.event: ~7 rows (approximately)
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
REPLACE INTO `event` (`UserID`, `EventID`, `EventName`, `Description`, `Date`, `Day`, `Colour`, `StartTime`, `Hidden`, `EndTime`) VALUES
	(2, 1, 's', '', NULL, 1, -16777216, 4, b'0', 5.5),
	(3, 1, 'Test', 'uytg', NULL, 1, 1, 15, b'0', 16),
	(2, 2, 'zfa', 'sfa', NULL, 4, -6750055, 2.5, b'0', 3.5),
	(2, 3, 'ffff', 'asdfgh', NULL, 5, -13108, 1.5, b'0', 2.5),
	(2, 4, '21552', '5252', NULL, 7, -6711040, 0, b'0', 0),
	(2, 5, 'thu', 'thudesc', NULL, 4, -13434625, 4.5, b'0', 21.5),
	(2, 6, 'eventtest', 'hfth', NULL, 1, -65536, 6, b'0', 6.5),
	(2, 8, 'testdate', 'testdate', '2018-01-04', NULL, -26215, 3, b'0', 5);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;


-- Dumping structure for table smarttimetabledb.task
CREATE TABLE IF NOT EXISTS `task` (
  `UserID` smallint(6) unsigned NOT NULL,
  `TaskID` smallint(6) unsigned NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Description` text,
  `CategoryID` smallint(6) unsigned NOT NULL,
  `DateSet` date NOT NULL,
  `DateDue` date NOT NULL,
  `Colour` int(11) NOT NULL,
  `Hidden` bit(1) NOT NULL DEFAULT b'0',
  `TimeSet` float unsigned NOT NULL,
  `TimeModified` float unsigned NOT NULL,
  `TimeUsed` float unsigned NOT NULL DEFAULT '0',
  `HighPriority` bit(1) NOT NULL DEFAULT b'0',
  `SlotsAssigned` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`TaskID`,`UserID`),
  KEY `task_CategoryID_idx` (`CategoryID`),
  KEY `FK_UserID2_idx` (`UserID`),
  CONSTRAINT `FK_task_category_2` FOREIGN KEY (`UserID`) REFERENCES `category` (`UserID`),
  CONSTRAINT `FK_task_user` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table smarttimetabledb.task: ~2 rows (approximately)
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
REPLACE INTO `task` (`UserID`, `TaskID`, `Name`, `Description`, `CategoryID`, `DateSet`, `DateDue`, `Colour`, `Hidden`, `TimeSet`, `TimeModified`, `TimeUsed`, `HighPriority`, `SlotsAssigned`) VALUES
	(2, 3, 'earlys', 'arsd', 1, '2017-08-15', '3000-05-10', -3342490, b'0', 1, 1, 0, b'1', 2),
	(2, 4, 'earlys', 'arsd', 1, '2017-08-15', '2999-05-10', -6750055, b'0', 1, 4.5, 2, b'0', 9);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;


-- Dumping structure for table smarttimetabledb.timetable
CREATE TABLE IF NOT EXISTS `timetable` (
  `UserID` smallint(5) unsigned NOT NULL,
  `TimetableID` smallint(5) unsigned NOT NULL,
  `Hidden` bit(1) NOT NULL DEFAULT b'0',
  `StartDay` date NOT NULL,
  PRIMARY KEY (`UserID`,`TimetableID`),
  CONSTRAINT `FK_timetable_user` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table smarttimetabledb.timetable: ~0 rows (approximately)
/*!40000 ALTER TABLE `timetable` DISABLE KEYS */;
REPLACE INTO `timetable` (`UserID`, `TimetableID`, `Hidden`, `StartDay`) VALUES
	(2, 1, b'0', '2018-01-29');
/*!40000 ALTER TABLE `timetable` ENABLE KEYS */;


-- Dumping structure for table smarttimetabledb.timetableslot
CREATE TABLE IF NOT EXISTS `timetableslot` (
  `UserID` smallint(5) unsigned NOT NULL,
  `TimetableID` smallint(5) unsigned NOT NULL,
  `Day` tinyint(3) unsigned NOT NULL,
  `Time` int(10) unsigned NOT NULL,
  `TaskID` smallint(5) unsigned DEFAULT NULL,
  `EventID` smallint(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`UserID`,`TimetableID`,`Day`,`Time`),
  KEY `FK_timetableslot_task` (`UserID`,`TaskID`),
  KEY `FK_timetableslot_event` (`UserID`,`EventID`),
  CONSTRAINT `FK_timetableslot_event` FOREIGN KEY (`UserID`, `EventID`) REFERENCES `event` (`UserID`, `EventID`),
  CONSTRAINT `FK_timetableslot_task` FOREIGN KEY (`UserID`, `TaskID`) REFERENCES `task` (`UserID`, `TaskID`),
  CONSTRAINT `FK_timetableslot_timetable` FOREIGN KEY (`UserID`, `TimetableID`) REFERENCES `timetable` (`UserID`, `TimetableID`),
  CONSTRAINT `FK_timetableslot_user` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table smarttimetabledb.timetableslot: ~55 rows (approximately)
/*!40000 ALTER TABLE `timetableslot` DISABLE KEYS */;
REPLACE INTO `timetableslot` (`UserID`, `TimetableID`, `Day`, `Time`, `TaskID`, `EventID`) VALUES
	(2, 1, 1, 8, NULL, 1),
	(2, 1, 1, 9, NULL, 1),
	(2, 1, 1, 10, NULL, 1),
	(2, 1, 1, 11, NULL, 1),
	(2, 1, 1, 12, NULL, 6),
	(2, 1, 1, 13, NULL, 6),
	(2, 1, 4, 5, NULL, 2),
	(2, 1, 4, 6, NULL, 2),
	(2, 1, 4, 7, NULL, 2),
	(2, 1, 4, 9, NULL, 5),
	(2, 1, 4, 10, NULL, 5),
	(2, 1, 4, 11, NULL, 5),
	(2, 1, 4, 12, NULL, 5),
	(2, 1, 4, 13, NULL, 5),
	(2, 1, 4, 14, NULL, 5),
	(2, 1, 4, 15, NULL, 5),
	(2, 1, 4, 16, NULL, 5),
	(2, 1, 4, 17, NULL, 5),
	(2, 1, 4, 18, NULL, 5),
	(2, 1, 4, 19, NULL, 5),
	(2, 1, 4, 20, NULL, 5),
	(2, 1, 4, 21, NULL, 5),
	(2, 1, 4, 22, NULL, 5),
	(2, 1, 4, 23, NULL, 5),
	(2, 1, 4, 24, NULL, 5),
	(2, 1, 4, 25, NULL, 5),
	(2, 1, 4, 26, NULL, 5),
	(2, 1, 4, 27, NULL, 5),
	(2, 1, 4, 28, NULL, 5),
	(2, 1, 4, 29, NULL, 5),
	(2, 1, 4, 30, NULL, 5),
	(2, 1, 4, 31, NULL, 5),
	(2, 1, 4, 32, NULL, 5),
	(2, 1, 4, 33, NULL, 5),
	(2, 1, 4, 34, NULL, 5),
	(2, 1, 4, 35, NULL, 5),
	(2, 1, 4, 36, NULL, 5),
	(2, 1, 4, 37, NULL, 5),
	(2, 1, 4, 38, NULL, 5),
	(2, 1, 4, 39, NULL, 5),
	(2, 1, 4, 40, NULL, 5),
	(2, 1, 4, 41, NULL, 5),
	(2, 1, 4, 42, NULL, 5),
	(2, 1, 4, 43, NULL, 5),
	(2, 1, 5, 3, NULL, 3),
	(2, 1, 5, 4, NULL, 3),
	(2, 1, 5, 5, NULL, 3),
	(2, 1, 5, 32, 4, NULL),
	(2, 1, 5, 35, 4, NULL),
	(2, 1, 5, 37, 3, NULL),
	(2, 1, 6, 13, 4, NULL),
	(2, 1, 6, 23, 4, NULL),
	(2, 1, 6, 25, 4, NULL),
	(2, 1, 7, 0, NULL, 4),
	(2, 1, 7, 17, 3, NULL);
/*!40000 ALTER TABLE `timetableslot` ENABLE KEYS */;


-- Dumping structure for table smarttimetabledb.user
CREATE TABLE IF NOT EXISTS `user` (
  `UserID` smallint(6) unsigned NOT NULL,
  `Username` varchar(15) NOT NULL,
  `Password` varchar(15) DEFAULT NULL,
  `Question` text NOT NULL,
  `Answer` text NOT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table smarttimetabledb.user: ~4 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`UserID`, `Username`, `Password`, `Question`, `Answer`) VALUES
	(1, 'asdf', 'asdf', '', ''),
	(2, 'sas', '', '', ''),
	(3, 'as', '123', '', ''),
	(4, 'qwerty', '', '', '');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for trigger smarttimetabledb.check_event_date_day_mutually_exclusive_on_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `check_event_date_day_mutually_exclusive_on_insert` BEFORE INSERT ON `event` FOR EACH ROW BEGIN
	IF (NEW.`Day` IS NOT NULL AND NEW.`Date` IS NOT NULL) OR (NEW.`Day` IS NULL AND NEW.`Date` IS NULL) THEN
		SIGNAL SQLSTATE VALUE '45000'
			SET MESSAGE_TEXT = 'Either Day or Date must have a value and the other must be null.';
	END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;


-- Dumping structure for trigger smarttimetabledb.check_event_date_day_mutually_exclusive_on_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `check_event_date_day_mutually_exclusive_on_update` BEFORE UPDATE ON `event` FOR EACH ROW BEGIN
	IF (NEW.`Day` IS NOT NULL AND NEW.`Date` IS NOT NULL) OR (NEW.`Day` IS NULL AND NEW.`Date` IS NULL) THEN
		SIGNAL SQLSTATE VALUE '45000'
			SET MESSAGE_TEXT = 'Either Day or Date must have a value and the other must be null.';
	END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;


-- Dumping structure for trigger smarttimetabledb.check_task_or_event_id_mutually_exclusive_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `check_task_or_event_id_mutually_exclusive_insert` BEFORE INSERT ON `timetableslot` FOR EACH ROW BEGIN
	IF (NEW.`EventID` IS NOT NULL AND NEW.`TaskID` IS NOT NULL) OR (NEW.`EventID` IS NULL AND NEW.`TaskID` IS NULL) THEN
		SIGNAL SQLSTATE VALUE '45000'
			SET MESSAGE_TEXT = 'Either TaskID or EventID must have a value and the other must be null.';
	END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;


-- Dumping structure for trigger smarttimetabledb.check_task_or_event_id_mutually_exclusive_on_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `check_task_or_event_id_mutually_exclusive_on_update` BEFORE UPDATE ON `timetableslot` FOR EACH ROW BEGIN
	IF (NEW.`EventID` IS NOT NULL AND NEW.`TaskID` IS NOT NULL) OR (NEW.`EventID` IS NULL AND NEW.`TaskID` IS NULL) THEN
		SIGNAL SQLSTATE VALUE '45000'
			SET MESSAGE_TEXT = 'Either TaskID or EventID must have a value and the other must be null.';
	END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
