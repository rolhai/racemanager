CREATE TABLE `countries` (
  `id` bigint NOT NULL,
  `name` varchar(45) NOT NULL,
  `isoCode` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isoCode_UNIQUE` (`isoCode`)
);

CREATE TABLE `drivers` (
  `id` bigint NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `countryId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `firstname_UNIQUE` (`firstname`),
  UNIQUE KEY `lastname_UNIQUE` (`lastname`)
);

