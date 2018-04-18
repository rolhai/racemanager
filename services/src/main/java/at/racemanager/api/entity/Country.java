package at.racemanager.api.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
@NamedQueries({
    @NamedQuery(name = Country.FIND_ALL, query = "FROM Country")
    ,
    @NamedQuery(name = Country.COUNT_RESULTS, query = "SELECT COUNT(c) FROM Country c")
})
public class Country extends ApiEntity {

    public static final String FIND_ALL = "Country.findAll";

    public static final String COUNT_RESULTS = "Country.countResults";

    /**
     * Country Codes - ISO 3166 Alpha2-Code c
     *
     * unique
     */
    private String isoCode;

    private String name;

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
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
        hash = 53 * hash + Objects.hashCode(this.isoCode);
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
        final Country other = (Country) obj;
        if (!Objects.equals(this.isoCode, other.isoCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", isoCode=" + isoCode + ", name=" + name + '}';
    }
}
