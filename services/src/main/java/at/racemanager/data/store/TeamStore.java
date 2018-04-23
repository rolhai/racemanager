/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.data.store;

import at.racemanager.api.entity.Team;
import javax.ejb.Stateless;

/**
 *
 * @author rolhai
 */
@Stateless
public class TeamStore extends DataStore<Team> {

    @Override
    protected String getCountResultNamedQuery() {
        return Team.COUNT_RESULTS;
    }

    @Override
    protected String getFindAllNamedQuery() {
        return Team.FIND_ALL;
    }

}
