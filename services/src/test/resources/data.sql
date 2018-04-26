DELETE FROM `countries`;

INSERT INTO `countries` (`id`, `name`, `isoCode`)
VALUES (1, 'Spain', 'ES');

INSERT INTO `countries` (`id`,`name`,`isoCode`)
VALUES (2, 'Germany', 'DE');

INSERT INTO `countries` (`id`,`name`,`isoCode`)
VALUES (3, 'Finland', 'FI');

INSERT INTO `countries` (`id`,`name`,`isoCode`)
VALUES (4, 'Italy', 'IT');

INSERT INTO `countries` (`id`,`name`,`isoCode`)
VALUES (5, 'Austria', 'AT');

INSERT INTO `countries` (`id`,`name`,`isoCode`)
VALUES (6, 'Great Britain', 'GB');

INSERT INTO `countries` (`id`,`name`,`isoCode`)
VALUES (7, 'Netherlands', 'NL');

DELETE FROM `drivers`;

INSERT INTO `drivers` (`id`,`firstname`,`lastname`,`dateOfBirth`,`countryId`)
VALUES (1, 'Michael', 'Schumacher', null, 2);

INSERT INTO `drivers` (`id`,`firstname`,`lastname`,`dateOfBirth`,`countryId`)
VALUES (2, 'Mika', 'Hakkinen', null, 3);

INSERT INTO `drivers` (`id`,`firstname`,`lastname`,`dateOfBirth`,`countryId`)
VALUES (3, 'Fernando', 'Alonso', null, 1);

INSERT INTO `drivers` (`id`,`firstname`,`lastname`,`dateOfBirth`,`countryId`)
VALUES (4, 'Max', 'Verstappen', null, 7);

DELETE FROM `tracks`;

INSERT INTO `tracks` (`id`, `name`, `countryId`)
VALUES (1, 'Hockenheim', 2);

INSERT INTO `tracks` (`id`, `name`, `countryId`)
VALUES (2, 'Barcelona', 1);

INSERT INTO `tracks` (`id`, `name`, `countryId`)
VALUES (3, 'Monza', 4);

DELETE FROM `teams`;

INSERT INTO `teams` (`id`, `year`, `name`, `motor`, `countryId`)
VALUES (1, 2018, 'Red Bull Racing', 'Renault', 5);

INSERT INTO `teams` (`id`, `year`, `name`, `motor`, `countryId`)
VALUES (2, 2017, 'McLaren', 'Honda', 6);

INSERT INTO `teams` (`id`, `year`, `name`, `motor`, `countryId`)
VALUES (3, 2018, 'McLaren', 'Renault', 6);

DELETE FROM `teams_drivers`;

INSERT INTO `teams_drivers` (`teamId`, `driverId`)
VALUES (1, 4);

INSERT INTO `teams_drivers` (`teamId`, `driverId`)
VALUES (2, 3);

INSERT INTO `teams_drivers` (`teamId`, `driverId`)
VALUES (3, 3);




