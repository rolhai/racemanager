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
package at.racemanager.api.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "championships")
@NamedQueries({
    @NamedQuery(name = Championship.FIND_ALL, query = "FROM Championship")
    ,
    @NamedQuery(name = Championship.COUNT_RESULTS, query = "SELECT COUNT(c) FROM Championship c")
})
public class Championship extends ApiEntity {

    public static final String FIND_ALL = "Championship.findAll";

    public static final String COUNT_RESULTS = "Championship.countResults";

    /**
     * unique
     */
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    private int year;

    private boolean template;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "championshipteams",
            joinColumns = {
                @JoinColumn(name = "championshipId", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "teamId", referencedColumnName = "id", unique = true)}
    )
    private Set<Team> teams;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "championship",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Race> races;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTemplate() {
        return template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Race> getRaces() {
        return races;
    }

    public void setRaces(Set<Race> races) {
        this.races = races;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.startDate);
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
        final Championship other = (Championship) obj;
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Championship{" + "name=" + name + ", template=" + template + ", startDate=" + startDate + ", endDate=" + endDate + ", year=" + year + '}';
    }
}
