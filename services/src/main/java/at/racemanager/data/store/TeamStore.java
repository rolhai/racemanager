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
package at.racemanager.data.store;

import at.racemanager.api.entity.Driver;
import at.racemanager.api.entity.RmEntityNotFoundException;
import at.racemanager.api.entity.RmException;
import at.racemanager.api.entity.RmParameterException;
import at.racemanager.api.entity.Team;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * crud-operations for teams
 *
 * @author rolhai
 */
@Stateless
public class TeamStore extends DataStore<Team> {

    @Inject
    private DriverStore driverStore;

    @Override
    protected String getCountResultNamedQuery() {
        return Team.COUNT_RESULTS;
    }

    @Override
    protected String getFindAllNamedQuery() {
        return Team.FIND_ALL;
    }

    public Team addDriver(long teamId, long driverId) throws RmException {
        if (teamId <= 0) {
            throw new RmParameterException("teamId", StoreError.IS_INVALID);
        }
        if (driverId <= 0) {
            throw new RmParameterException("driverId", StoreError.IS_INVALID);
        }
        Team team = findById(teamId);
        if (team == null) {
            throw new RmEntityNotFoundException(Team.class, StoreError.NOT_FOUND);
        }
        Driver driver = driverStore.findById(driverId);
        if (driver == null) {
            throw new RmEntityNotFoundException(Driver.class, StoreError.NOT_FOUND);
        }
        team.addDriver(driver);
        return update(team);
    }

    public Team removeDriver(long teamId, long driverId) throws RmException {
        if (teamId <= 0) {
            throw new RmParameterException("teamId", StoreError.IS_INVALID);
        }
        if (driverId <= 0) {
            throw new RmParameterException("driverId", StoreError.IS_INVALID);
        }
        Team team = findById(teamId);
        if (team == null) {
            throw new RmEntityNotFoundException(Team.class, StoreError.NOT_FOUND);
        }
        team.removeDriver(driverId);
        return update(team);
    }

}
