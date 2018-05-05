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
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "raceresults")
public class RaceResult extends ApiEntity {

    @ManyToOne
    @JoinColumn(name = "driverId")
    @NotNull
    private Driver driver;

    private int qualifyingPosition;

    private LocalTime qualifyingLapTime;

    private int racePosition;

    private LocalTime raceLapTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raceId")
    private Race race;

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getQualifyingPosition() {
        return qualifyingPosition;
    }

    public void setQualifyingPosition(int qualifyingPosition) {
        this.qualifyingPosition = qualifyingPosition;
    }

    public LocalTime getQualifyingLapTime() {
        return qualifyingLapTime;
    }

    public void setQualifyingLapTime(LocalTime qualifyingLapTime) {
        this.qualifyingLapTime = qualifyingLapTime;
    }

    public int getRacePosition() {
        return racePosition;
    }

    public void setRacePosition(int racePosition) {
        this.racePosition = racePosition;
    }

    public LocalTime getRaceLapTime() {
        return raceLapTime;
    }

    public void setRaceLapTime(LocalTime raceLapTime) {
        this.raceLapTime = raceLapTime;
    }

    @Override
    public String toString() {
        return "RaceResult{" + "driver=" + driver + ", qualifyingPosition=" + qualifyingPosition + ", qualifyingLapTime=" + qualifyingLapTime + ", racePosition=" + racePosition + ", raceLapTime=" + raceLapTime + '}';
    }
}
