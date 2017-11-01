package at.racemanager.api.model;

import java.util.Objects;

public class Team {

    private String name;

    private Team firstDriver;

    private Team secondDriver;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getFirstDriver() {
        return firstDriver;
    }

    public void setFirstDriver(Team firstDriver) {
        this.firstDriver = firstDriver;
    }

    public Team getSecondDriver() {
        return secondDriver;
    }

    public void setSecondDriver(Team secondDriver) {
        this.secondDriver = secondDriver;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.name);
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
        final Team other = (Team) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Team{" + "name=" + name + ", firstDriver=" + firstDriver + ", secondDriver=" + secondDriver + '}';
    }
}
