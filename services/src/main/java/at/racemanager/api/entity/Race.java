package at.racemanager.api.entity;

import java.time.LocalDate;
import java.util.List;

public class Race {

    private Long id;

    private LocalDate raceDate;

    private Track track;

    private List<RaceResult> raceResults;

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
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
