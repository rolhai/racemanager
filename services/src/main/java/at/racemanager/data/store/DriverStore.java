/*
 * Copyright 2018 rolhai.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.racemanager.data.store;

import at.racemanager.api.entity.Country;
import at.racemanager.api.entity.Driver;
import at.racemanager.api.entity.RmException;
import at.racemanager.api.entity.RmParameterException;

import java.util.logging.Logger;

import javax.ejb.Stateless;

/**
 * crud-operations for drivers
 *
 * @author rolhai
 */
@Stateless
public class DriverStore extends DataStore<Driver> {

    public static final Logger logger = Logger.getLogger(DriverStore.class.getName());

    @Override
    protected String getFindAllNamedQuery() {
        return Driver.FIND_ALL;
    }

    @Override
    protected String getCountResultNamedQuery() {
        return Driver.COUNT_RESULTS;
    }

    @Override
    public Driver create(Driver driver) throws RmException {
        // a country must be provided to create a driver
        if (driver == null) {
            throw new RmParameterException(Driver.class, StoreError.NOT_PROVIDED);
        } else if (driver.getCountry() == null) {
            throw new RmParameterException(Country.class, StoreError.NOT_PROVIDED);
        } else if (driver.getCountry().getId() == null
                || driver.getCountry().getId() <= 0) {
            throw new RmParameterException("country.id", StoreError.IS_INVALID);
        }
        Country country = em.find(Country.class, driver.getCountry().getId());
        if (country == null) {
            throw new RmParameterException(Country.class, StoreError.NOT_FOUND);
        }
        driver.setCountry(country);
        em.persist(driver);
        logger.info(String.format("created driver with id ", driver.getId()));
        return driver;
    }

    /*
    List<Driver> drivers = new ArrayList<>();

    Country spain = new Country();
    spain.setIsoCode("ES");
    spain.setName("Spain");

    Country germany = new Country();
    germany.setIsoCode("DE");
    germany.setName("Germany");

    Country greatBritain = new Country();
    greatBritain.setIsoCode("GB");
    greatBritain.setName("United Kingdom of Great Britain and Northern Ireland");

    Driver alonso = new Driver();
    alonso.setFirstname("Fernando");
    alonso.setLastname("Alonso");
    alonso.setDateOfBirth(LocalDate.of(1981, 7, 29));
    alonso.setCountry(spain);

    Driver vettel = new Driver();
    vettel.setFirstname("Sebastian");
    vettel.setLastname("Vettel");
    vettel.setDateOfBirth(LocalDate.of(1987, 7, 3));
    vettel.setCountry(germany);

    Driver hamilton = new Driver();
    hamilton.setFirstname("Lewis");
    hamilton.setLastname("Hamilton");
    hamilton.setDateOfBirth(LocalDate.of(1985, 1, 7));
    hamilton.setCountry(greatBritain);

    drivers.add(alonso);
    drivers.add(vettel);
    drivers.add(hamilton);
    return drivers;
     */
}
