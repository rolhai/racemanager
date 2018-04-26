/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.data.store;

import at.racemanager.api.entity.Race;
import javax.ejb.Stateless;

/**
 *
 * @author rolhai
 */
@Stateless
public class RaceStore extends DataStore<Race> {

    @Override
    protected String getCountResultNamedQuery() {
        return Race.COUNT_RESULTS;
    }

    @Override
    protected String getFindAllNamedQuery() {
        return Race.FIND_ALL;
    }

}
