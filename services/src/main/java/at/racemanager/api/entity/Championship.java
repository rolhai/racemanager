package at.racemanager.api.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Championship {

    private Long id;

    private boolean template;

    /**
     * unique
     */
    private LocalDate startDate;

    private LocalDate endDate;

    private int year;

    private List<Team> teams;

    private List<Race> races;

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
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
        return "Championship{" + "id=" + id + ", template=" + template + ", startDate=" + startDate + ", endDate=" + endDate + ", year=" + year + ", teams=" + teams + ", races=" + races + '}';
    }
}
