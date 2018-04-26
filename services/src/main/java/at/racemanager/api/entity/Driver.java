package at.racemanager.api.entity;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "drivers")
@NamedQueries({
    @NamedQuery(name = Driver.FIND_ALL, query = "FROM Driver") // JOIN d.country
    ,
    @NamedQuery(name = Driver.COUNT_RESULTS, query = "SELECT COUNT(d) FROM Driver d")
})
public class Driver extends ApiEntity {

    public static final String FIND_ALL = "Driver.findAll";

    public static final String COUNT_RESULTS = "Driver.countResults";

    /**
     * unique
     */
    @NotNull
    @Size(min = 2, max = 100)
    private String firstname;

    /**
     * unique
     */
    @NotNull
    @Size(min = 2, max = 100)
    private String lastname;

    private LocalDate dateOfBirth;

    @ManyToOne//(fetch = FetchType.LAZY)
    //@Fetch(FetchMode.JOIN)
    @JoinColumn(name = "countryId")
    @NotNull
    private Country country;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.firstname);
        hash = 13 * hash + Objects.hashCode(this.lastname);
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
        final Driver other = (Driver) obj;
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Driver{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", dateOfBirth=" + dateOfBirth + ", country=" + country + '}';
    }
}
