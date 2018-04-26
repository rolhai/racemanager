package at.racemanager.api.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teams")
@NamedQueries({
    @NamedQuery(name = Team.FIND_ALL, query = "FROM Team")
    ,
    @NamedQuery(name = Team.COUNT_RESULTS, query = "SELECT COUNT(t) FROM Team t")
})
public class Team extends ApiEntity {

    public static final String FIND_ALL = "Team.findAll";

    public static final String COUNT_RESULTS = "Team.countResults";

    /**
     * unique
     */
    private int year;

    /**
     * unique
     */
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Size(min = 2, max = 100)
    private String motor;

    @OneToMany
    @JoinTable(
            name = "teams_drivers",
            joinColumns = {
                @JoinColumn(name = "teamId", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "driverId", referencedColumnName = "id", unique = true)}
    )
    private List<Driver> drivers;

    @ManyToOne
    @JoinColumn(name = "countryId")
    @NotNull
    private Country country;

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
