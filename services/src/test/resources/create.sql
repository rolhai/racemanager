CREATE TABLE `countries` (
  `id` bigint NOT NULL,
  `name` varchar(45) NOT NULL,
  `isoCode` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `country_name_UNIQUE` (`name`),
  UNIQUE KEY `country_isoCode_UNIQUE` (`isoCode`)
);

CREATE TABLE `drivers` (
  `id` bigint NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `countryId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `driver_UNIQUE` (`firstname`, `lastname`)
);

CREATE TABLE `tracks` (
  `id` bigint NOT NULL,
  `name` varchar(100) NOT NULL,
  `countryId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `track_UNIQUE` (`name`)
);

CREATE TABLE `teams` (
  `id` bigint NOT NULL,
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

