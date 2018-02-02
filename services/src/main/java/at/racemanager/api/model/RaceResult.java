package at.racemanager.api.model;

import java.time.LocalTime;

public class RaceResult {

    private Long id;

    private Driver driver;

    private int qualifyingPosition;

    private LocalTime qualifyingLapTime;

    private int racePosition;

    private LocalTime raceLapTime;

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
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
        return "RaceResult{" + "id=" + id + ", driver=" + driver + ", qualifyingPosition=" + qualifyingPosition + ", qualifyingLapTime=" + qualifyingLapTime + ", racePosition=" + racePosition + ", raceLapTime=" + raceLapTime + '}';
    }
}
