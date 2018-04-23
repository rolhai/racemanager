/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.data.store;

import at.racemanager.api.entity.Track;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rolhai
 */
public class TrackStoreTest extends StoreTest {

    @Test
    public void getAllTracks() {
        List<Track> entities = em.createQuery("FROM Track", Track.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(3, entities.size());

        Track entity = entities.get(0);
        Assert.assertNotNull(entity.getCountry());
    }
}
