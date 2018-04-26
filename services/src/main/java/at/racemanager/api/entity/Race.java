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
import java.util.List;
import javax.validation.constraints.NotNull;

/*
@Entity
@Table(name = "races")
@NamedQueries({
    @NamedQuery(name = Race.FIND_ALL, query = "FROM Race")
    ,
    @NamedQuery(name = Race.COUNT_RESULTS, query = "SELECT COUNT(r) FROM Race r")
})
 */
public class Race extends ApiEntity {

    public static final String FIND_ALL = "Race.findAll";

    public static final String COUNT_RESULTS = "Race.countResults";

    @NotNull
    private LocalDate raceDate;

    @NotNull
    private Track track;

    private List<RaceResult> raceResults;

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

    public List<RaceResult> getRaceResults() {
        return raceResults;
    }

    public void setRaceResults(List<RaceResult> raceResults) {
        this.raceResults = raceResults;
    }

    @Override
    public String toString() {
        return "Race{" + "id=" + id + ", raceDate=" + raceDate + ", track=" + track + ", raceResults=" + raceResults + '}';
    }
}
