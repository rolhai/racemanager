/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.data.store;

import at.racemanager.api.entity.Track;
import javax.ejb.Stateless;

/**
 *
 * @author rolhai
 */
@Stateless
public class TrackStore extends DataStore<Track> {

    @Override
    protected String getCountResultNamedQuery() {
        return Track.COUNT_RESULTS;
    }

    @Override
    protected String getFindAllNamedQuery() {
        return Track.FIND_ALL;
    }

}
