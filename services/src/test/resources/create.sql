CREATE TABLE countries (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  isoCode varchar(2) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY country_name_UNIQUE (name),
  UNIQUE KEY country_isoCode_UNIQUE (isoCode)
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
  UNIQUE KEY `track_UNIQUE` (`name`),
  CONSTRAINT fk_tracks_countries FOREIGN KEY (countryId) REFERENCES countries(id)
);

CREATE TABLE `teams` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `year` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `motor` varchar(100) NOT NULL,
  `countryId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `team_UNIQUE` (`year`,`name`),
  CONSTRAINT fk_teams_countries FOREIGN KEY (countryId) REFERENCES countries(id)
);

CREATE TABLE `teamdrivers` (
  `driverId` bigint NOT NULL,
  `teamId` bigint NOT NULL,
  UNIQUE KEY `teamdrivers_UNIQUE` (`teamId`,`driverId`),
  CONSTRAINT fk_teamdrivers_drivers FOREIGN KEY (driverId) REFERENCES drivers(id),
  CONSTRAINT fk_teamdrivers_teams FOREIGN KEY (teamId) REFERENCES teams(id)
);

CREATE TABLE `races` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `raceDate` date NOT NULL,
  `trackId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `races_UNIQUE` (`raceDate`,`trackId`),
  CONSTRAINT fk_races_tracks FOREIGN KEY (trackId) REFERENCES tracks(id)
);

CREATE TABLE `raceresults` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `raceId` bigint NOT NULL,
  `driverId` bigint NOT NULL,
  `qualifyingPosition` int DEFAULT NULL,
  `qualifyingLapTime` time DEFAULT NULL,
  `racePosition` int DEFAULT NULL,
  `raceLapTime` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `raceresults_UNIQUE` (`raceId`,`driverId`),
  CONSTRAINT fk_raceresults_races FOREIGN KEY (raceId) REFERENCES races(id),
  CONSTRAINT fk_raceresults_drivers FOREIGN KEY (driverId) REFERENCES drivers(id)
);
