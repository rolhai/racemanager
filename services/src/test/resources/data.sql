DELETE FROM `countries`;

INSERT INTO `countries` (`id`, `name`, `isoCode`)
VALUES (1, 'Spain', 'ES');

INSERT INTO `countries` (`id`,`name`,`isoCode`)
VALUES (2, 'Germany', 'DE');

INSERT INTO `countries` (`id`,`name`,`isoCode`)
VALUES (3, 'Finland', 'FI');

DELETE FROM `drivers`;

INSERT INTO `drivers` (`id`,`firstname`,`lastname`,`dateOfBirth`,`countryId`)
VALUES (1, 'Michael', 'Schumacher', null, 2);

INSERT INTO `drivers` (`id`,`firstname`,`lastname`,`dateOfBirth`,`countryId`)
VALUES (2, 'Mika', 'Hakkinen', null, 3);

INSERT INTO `drivers` (`id`,`firstname`,`lastname`,`dateOfBirth`,`countryId`)
VALUES (3, 'Fernando', 'Alonso', null, 1);



