/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.data.store;

import at.racemanager.api.entity.Driver;
import at.racemanager.api.entity.Team;
import static at.racemanager.data.store.StoreTest.em;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rolhai
 */
public class TeamStoreTest extends StoreTest {

    private static final Logger logger = LogManager.getLogger(TeamStoreTest.class);

    @Test
    public void getAllTeams() {
        List<Team> entities = em.createQuery("FROM Team", Team.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(3, entities.size());

        Team t1 = entities.get(0);
        Assert.assertNotNull(t1);
        Driver d1 = t1.getDrivers().get(0);
        Assert.assertNotNull(d1);

        logger.debug(entities.toString());

    }
}
