package at.racemanager.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Team {

    private Long id;

    /**
     * unique
     */
    private int year;

    /**
     * unique
     */
    private String name;

    private String motor;

    private List<Driver> drivers;

    private Country country;

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void addDriver(Driver driver) {
        if (drivers == null) {
            drivers = new ArrayList<>();
        }
        if (drivers.contains(driver)) {
            return;
        }
        drivers.add(driver);
    }

    public void removeDriver(Long id) {
        if (drivers == null) {
            return;
        }
        Optional<Driver> filterResult = drivers.stream().filter(d -> Objects.equals(d.getId(), id)).findAny();
        if (filterResult == null || !filterResult.isPresent()) {
            return;
        }
        drivers.remove(filterResult.get());
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + this.year;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Team other = (Team) obj;
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Team{" + "id=" + id + ", name=" + name + ", motor=" + motor + ", year=" + year + ", drivers=" + drivers + ", country=" + country + '}';
    }
}
