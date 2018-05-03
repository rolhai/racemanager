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

import at.racemanager.api.entity.Country;
import at.racemanager.api.entity.Driver;
import at.racemanager.api.entity.Team;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import static at.racemanager.data.store.StoreTest.em;

/**
 *
 * @author rolhai
 */
public class TeamStoreTest extends StoreTest {

    private static final Logger logger = LogManager.getLogger(TeamStoreTest.class);

    @Test
    public void testTeams() {
        int initSize = 3;

        List<Team> entities = em.createNamedQuery(Team.FIND_ALL, Team.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());

        Team entity = new Team();
        entity.setYear((short) 2018);
        entity.setName("Red Bull Racing");
        entity.setMotor("Renault");
        Country country = em.createNamedQuery(Country.FIND_BY_ISOCODE, Country.class)
                .setParameter("isoCode", "AT")
                .getSingleResult();
        entity.setCountry(country);
        Driver driver = em.createNamedQuery(Driver.FIND_BY_LASTNAME, Driver.class)
                .setParameter("lastname", "Verstappen")
                .getSingleResult();
        entity.addDriver(driver);

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getId());
        logger.info(entity.toString());
        logger.info(entity.toString());

        entities = em.createNamedQuery(Team.FIND_ALL, Team.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize + 1, entities.size());

        entity.setName("Goldstone");
        Driver driver2 = em.createNamedQuery(Driver.FIND_BY_LASTNAME, Driver.class)
                .setParameter("lastname", "Ricciardo")
                .getSingleResult();
        entity.addDriver(driver2);
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        logger.info(entity.toString());
        logger.info(entity.toString());

        entities = em.createNamedQuery(Team.FIND_ALL, Team.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize + 1, entities.size());

        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();

        entities = em.createNamedQuery(Team.FIND_ALL, Team.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());
    }
}
