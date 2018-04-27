CREATE TABLE `countries` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `isoCode` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `country_name_UNIQUE` (`name`),
  UNIQUE KEY `country_isoCode_UNIQUE` (`isoCode`)
);

CREATE TABLE `drivers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `countryId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `driver_UNIQUE` (`firstname`, `lastname`)
);

CREATE TABLE `tracks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `countryId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `track_UNIQUE` (`name`)
);

CREATE TABLE `teams` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `year` int(4) NOT NULL,
  `name` varchar(100) NOT NULL,
  `motor` varchar(100) NOT NULL,
  `countryId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `team_UNIQUE` (`name`,`year`)
);

CREATE TABLE `teams_drivers` (
    `driverId` bigint NOT NULL,
    `teamId` bigint NOT NULL,
    UNIQUE KEY `team_driver_UNIQUE` (`teamId`,`driverId`)
);

CREATE TABLE `races` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `raceDate` date NOT NULL,
  `trackId` bigint NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `raceresults` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `driverId` bigint NOT NULL,
  `qualifyingPosition` int(2) DEFAULT NULL,
  `qualifyingLapTime` time DEFAULT NULL,
  `racePosition` int(2) DEFAULT NULL,
  `raceLapTime` time DEFAULT NULL,
  `raceId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `race_UNIQUE` (`raceId`,`driverId`)
);



