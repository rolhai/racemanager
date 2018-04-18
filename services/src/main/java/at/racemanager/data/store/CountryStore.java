/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.data.store;

import at.racemanager.api.entity.Country;
import javax.ejb.Stateless;

/**
 *
 * @author rolhai
 */
@Stateless
public class CountryStore extends DataStore<Country> {

    @Override
    protected String getFindAllNamedQuery() {
        return Country.FIND_ALL;
    }

    @Override
    protected String getCountResultNamedQuery() {
        return Country.COUNT_RESULTS;
    }
}
