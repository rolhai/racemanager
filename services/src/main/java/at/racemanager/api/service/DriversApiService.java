package at.racemanager.api.service;

import at.racemanager.api.model.Country;
import at.racemanager.api.model.Driver;
import java.time.LocalDate;

public class DriversApiService {

    public Driver getDriver(String namefilter) {
        Country country = new Country();
        country.setIsoCode("ES");
        country.setName("Spain");

        Driver driver = new Driver();
        driver.setFirstname("Fernando");
        driver.setLastname("Alonso");
        LocalDate date = LocalDate.of(1981, 7, 29);
        driver.setDateOfBirth(date);
        driver.setCountry(country);

        return driver;
    }
}
