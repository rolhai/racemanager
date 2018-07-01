-- delete all

DELETE FROM `raceresults`;
DELETE FROM `races`;
DELETE FROM `championshipteams`;
DELETE FROM `championships`;
DELETE FROM `teamdrivers`;
DELETE FROM `teams`;
DELETE FROM `drivers`;
DELETE FROM `tracks`;
DELETE FROM `countries`;

-- insert countries

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

-- insert drivers

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

-- insert tracks

INSERT INTO `tracks` (`name`, `countryId`)
VALUES ('Hockenheim', (SELECT `id` FROM `countries` where `isoCode` = 'DE'));

INSERT INTO `tracks` ( `name`, `countryId`)
VALUES ('Barcelona', (SELECT `id` FROM `countries` where `isoCode` = 'ES'));

INSERT INTO `tracks` ( `name`, `countryId`)
VALUES ('Monza', (SELECT `id` FROM `countries` where `isoCode` = 'IT'));

-- insert teams;

INSERT INTO `teams` (`year`, `name`, `motor`, `countryId`)
VALUES (2017, 'Red Bull Racing', 'Renault', (SELECT `id` FROM `countries` where `isoCode` = 'AT'));

INSERT INTO `teams` (`year`, `name`, `motor`, `countryId`)
VALUES (2017, 'McLaren', 'Honda', (SELECT `id` FROM `countries` where `isoCode` = 'GB'));

INSERT INTO `teams` (`year`, `name`, `motor`, `countryId`)
VALUES (2018, 'McLaren', 'Renault', (SELECT `id` FROM `countries` where `isoCode` = 'GB'));

-- insert drivers for teams

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

-- insert championship

INSERT INTO `championships` (`name`, `template`, `year`)
VALUES ('Formula 1 - Italian 2018', TRUE, 2018);

INSERT INTO `championships` (`name`, `template`, `year`)
VALUES ('Formula 1 - German 2017', TRUE, 2017);

INSERT INTO `championshipteams` (`championshipId`, `teamId`)
VALUES (
    (SELECT `id` FROM `championships` WHERE `name` = 'Formula 1 - German 2017' AND `year` = 2017),
    (SELECT `id` FROM `teams` WHERE `name` = 'Red Bull Racing' AND `year` = 2017)
);

INSERT INTO `championshipteams` (`championshipId`, `teamId`)
VALUES (
    (SELECT `id` FROM `championships` WHERE `name` = 'Formula 1 - German 2017' AND `year` = 2017),
    (SELECT `id` FROM `teams` WHERE `name` = 'McLaren' AND `year` = 2017)
);

INSERT INTO `championshipteams` (`championshipId`, `teamId`)
VALUES (
    (SELECT `id` FROM `championships` WHERE `name` = 'Formula 1 - Italian 2018' AND `year` = 2018),
    (SELECT `id` FROM `teams` WHERE `name` = 'McLaren' AND `year` = 2018)
);

-- insert races

INSERT INTO `races` (`raceDate`, `championshipId`, `trackId`)
VALUES (
    '2018-03-04',
    (SELECT `id` FROM `championships` WHERE `template` = TRUE AND `year` = 2018),
    (SELECT `id` FROM `tracks` WHERE `name` = 'Monza'));

INSERT INTO `races` (`raceDate`, `championshipId`, `trackId`)
VALUES (
    '2017-03-04',
    (SELECT `id` FROM `championships` WHERE `template` = TRUE AND `year` = 2017),
    (SELECT `id` FROM `tracks` WHERE `name` = 'Hockenheim'));

-- insert results for races

-- TODO