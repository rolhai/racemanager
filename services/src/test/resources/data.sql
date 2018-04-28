DELETE FROM `countries`;

INSERT INTO `countries` (`name`, `isoCode`)
VALUES ('Spain', 'ES');

INSERT INTO `countries` (`name`,`isoCode`)
VALUES ('Germany', 'DE');

INSERT INTO `countries` (`name`,`isoCode`)
VALUES ('Finland', 'FI');

INSERT INTO `countries` (`name`,`isoCode`)
VALUES ('Italy', 'IT');

INSERT INTO `countries` (`name`,`isoCode`)
VALUES ('Austria', 'AT');

INSERT INTO `countries` (`name`,`isoCode`)
VALUES ('Great Britain', 'GB');

INSERT INTO `countries` (`name`,`isoCode`)
VALUES ('Netherlands', 'NL');

INSERT INTO `countries` (`name`,`isoCode`)
VALUES ('Australia', 'AU');

DELETE FROM `drivers`;

INSERT INTO `drivers` (`firstname`,`lastname`,`dateOfBirth`,`countryId`)
VALUES ('Michael', 'Schumacher', null, (SELECT `id` FROM `countries` where `isoCode` = 'DE'));

INSERT INTO `drivers` (`firstname`,`lastname`,`dateOfBirth`,`countryId`)
VALUES ('Mika', 'Hakkinen', null, (SELECT `id` FROM `countries` where `isoCode` = 'FI'));

INSERT INTO `drivers` (`firstname`,`lastname`,`dateOfBirth`,`countryId`)
VALUES ('Fernando', 'Alonso', null, (SELECT `id` FROM `countries` where `isoCode` = 'ES'));

INSERT INTO `drivers` (`firstname`,`lastname`,`dateOfBirth`,`countryId`)
VALUES ('Max', 'Verstappen', null, (SELECT `id` FROM `countries` where `isoCode` = 'NL'));

INSERT INTO `drivers` (`firstname`,`lastname`,`dateOfBirth`,`countryId`)
VALUES ('Daniel', 'Ricciardo', null, (SELECT `id` FROM `countries` where `isoCode` = 'AU'));

DELETE FROM `tracks`;

INSERT INTO `tracks` (`name`, `countryId`)
VALUES ('Hockenheim', (SELECT `id` FROM `countries` where `isoCode` = 'DE'));

INSERT INTO `tracks` ( `name`, `countryId`)
VALUES ('Barcelona', (SELECT `id` FROM `countries` where `isoCode` = 'ES'));

INSERT INTO `tracks` ( `name`, `countryId`)
VALUES ('Monza', (SELECT `id` FROM `countries` where `isoCode` = 'IT'));

DELETE FROM `teams`;

INSERT INTO `teams` (`year`, `name`, `motor`, `countryId`)
VALUES (2017, 'Red Bull Racing', 'Renault', (SELECT `id` FROM `countries` where `isoCode` = 'AT'));

INSERT INTO `teams` (`year`, `name`, `motor`, `countryId`)
VALUES (2017, 'McLaren', 'Honda', (SELECT `id` FROM `countries` where `isoCode` = 'GB'));

INSERT INTO `teams` (`year`, `name`, `motor`, `countryId`)
VALUES (2018, 'McLaren', 'Renault', (SELECT `id` FROM `countries` where `isoCode` = 'GB'));

DELETE FROM `teamdrivers`;

INSERT INTO `teamdrivers` (`teamId`, `driverId`)
VALUES (
  (SELECT `id` FROM `teams` WHERE `year` = 2017 and `name` = 'Red Bull Racing'),
  (SELECT `id` FROM `drivers` WHERE `lastname` = 'Verstappen'));

INSERT INTO `teamdrivers` (`teamId`, `driverId`)
VALUES (
  (SELECT `id` FROM `teams` WHERE `year` = 2017 and `name` = 'McLaren'),
  (SELECT `id` FROM `drivers` WHERE `lastname` = 'Alonso'));

INSERT INTO `teamdrivers` (`teamId`, `driverId`)
VALUES (
  (SELECT `id` FROM `teams` WHERE `year` = 2018 and `name` = 'McLaren'),
  (SELECT `id` FROM `drivers` WHERE `lastname` = 'Alonso'));