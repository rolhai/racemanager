/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.data.store;

import at.racemanager.api.entity.Country;
import at.racemanager.api.entity.Driver;
import at.racemanager.api.entity.RmException;
import at.racemanager.api.entity.RmParameterException;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author rolhai
 */
@Stateless
public class DriverStore extends DataStore<Driver> {

    public static final Logger logger = LogManager.getLogger(DriverStore.class);

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
            throw new RmParameterException("driver", "not provided");
        } else if (driver.getCountry() == null) {
            throw new RmParameterException("country", "not provided");
        } else if (driver.getCountry().getId() == null
                || driver.getCountry().getId() <= 0) {
            throw new RmParameterException("country.id", "is invalid");
        }
        Country country = em.find(Country.class, driver.getCountry().getId());
        if (country == null) {
            throw new RmParameterException("country", "not found");
        }
        driver.setCountry(country);
        em.persist(driver);
        logger.debug(String.format("created driver with id ", driver.getId()));
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
