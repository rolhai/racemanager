package at.racemanager.api.service;

import at.racemanager.api.model.Country;
import at.racemanager.api.model.Driver;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DriversApiService {

    public List<Driver> getDrivers() {
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
    }
}
