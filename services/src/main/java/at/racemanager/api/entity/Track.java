package at.racemanager.api.entity;

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
@Table(name = "tracks")
@NamedQueries({
    @NamedQuery(name = Track.FIND_ALL, query = "FROM Track")
    ,
    @NamedQuery(name = Track.COUNT_RESULTS, query = "SELECT COUNT(t) FROM Track t")
})
public class Track extends ApiEntity {

    public static final String FIND_ALL = "Track.findAll";

    public static final String COUNT_RESULTS = "Track.countResults";

    /**
     * unique
     */
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "countryId")
    @NotNull
    private Country country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
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
        final Track other = (Track) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Track{" + "id=" + id + ", name=" + name + ", country=" + country + '}';
    }
}
