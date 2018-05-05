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

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "races")
@NamedQueries({
    @NamedQuery(name = Race.FIND_ALL, query = "FROM Race")
    ,
    @NamedQuery(name = Race.COUNT_RESULTS, query = "SELECT COUNT(r) FROM Race r")
})
public class Race extends ApiEntity {

    public static final String FIND_ALL = "Race.findAll";

    public static final String COUNT_RESULTS = "Race.countResults";

    @NotNull
    private LocalDate raceDate;

    @ManyToOne
    @JoinColumn(name = "trackId")
    @NotNull
    private Track track;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "championshipId")
    @NotNull
    private Championship championship;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "race",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<RaceResult> raceResults;

    public void addRaceResult(RaceResult raceresult) {
        if (raceResults == null) {
            raceResults = new HashSet<>();
        }
        if (raceResults.contains(raceresult)) {
            return;
        }
        raceResults.add(raceresult);
        raceresult.setRace(this);
    }

    public void removeRaceResult(Long id) {
        if (raceResults == null) {
            return;
        }
        Optional<RaceResult> filterResult = raceResults.stream().filter(d -> Objects.equals(d.getId(), id)).findAny();
        if (filterResult == null || !filterResult.isPresent()) {
            return;
        }
        RaceResult raceResult = filterResult.get();
        raceResults.remove(raceResult);
        raceResult.setRace(null);
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public LocalDate getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(LocalDate raceDate) {
        this.raceDate = raceDate;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Set<RaceResult> getRaceResults() {
        return raceResults;
    }

    public void setRaceResults(Set<RaceResult> raceResults) {
        this.raceResults = raceResults;
    }

    @Override
    public String toString() {
        return "Race{" + "id=" + id + ", raceDate=" + raceDate + ", track=" + track + ", raceResults=" + raceResults + '}';
    }
}
