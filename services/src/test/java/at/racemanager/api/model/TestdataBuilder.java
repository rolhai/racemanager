package at.racemanager.api.model;

import java.util.ArrayList;
import java.util.List;

public class TestdataBuilder {

    /**
     * create a list of drivers
     *
     * @return list of drivers
     */
    public List<Driver> getDrivers() {
        List<Driver> drivers = new ArrayList<>();

        Driver d1 = new Driver();
        d1.setId(1l);
        d1.setFirstname("Michael");
        d1.setLastname("Schumacher");
        drivers.add(d1);

        Driver d2 = new Driver();
        d1.setId(2l);
        d1.setFirstname("Mika");
        d1.setLastname("Hakkinen");
        drivers.add(d2);

        Driver d3 = new Driver();
        d3.setId(3l);
        d3.setFirstname("Damon");
        d3.setLastname("Hill");
        drivers.add(d3);

        return drivers;
    }
}
