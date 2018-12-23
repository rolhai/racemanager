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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import at.racemanager.api.entity.Championship;
import at.racemanager.api.entity.Driver;
import at.racemanager.api.entity.Race;
import at.racemanager.api.entity.RaceResult;
import at.racemanager.api.entity.Track;

/**
 * Tests for RaceStore
 *
 * @author rolhai
 */
public class RaceStoreTest extends StoreTest {

    private static final Logger logger = Logger.getLogger(RaceStoreTest.class.getName());

    @Test
    public void testRaces() {
        int initSize = 2;

        List<Race> entities = em.createNamedQuery(Race.FIND_ALL, Race.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());

        Race entity = new Race();
        entity.setRaceDate(LocalDate.of(2018, Month.MARCH, 17));
        Track track = em.createNamedQuery(Track.FIND_BY_NAME, Track.class)
                .setParameter("name", "Hockenheim")
                .getSingleResult();
        Championship championship = (Championship) em.createNamedQuery(Championship.FIND_ALL)
                .getResultList().stream().findFirst().get();
        entity.setChampionship(championship);
        entity.setTrack(track);

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getId());
        logger.info(entity.toString());

        RaceResult raceResult = new RaceResult();
        Driver driver = em.createNamedQuery(Driver.FIND_BY_LASTNAME, Driver.class)
                .setParameter("lastname", "Verstappen")
                .getSingleResult();
        raceResult.setDriver(driver);
        raceResult.setQualifyingPosition(3);
        raceResult.setQualifyingLapTime(LocalTime.of(1, 47, 17));
        raceResult.setRacePosition(2);
        raceResult.setRaceLapTime(LocalTime.of(1, 10, 23));
        entity.addRaceResult(raceResult);
        em.getTransaction().begin();
        em.persist(raceResult);
        em.getTransaction().commit();
        logger.info(raceResult.toString());

        entities = em.createNamedQuery(Race.FIND_ALL, Race.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize + 1, entities.size());

        entity.setRaceDate(LocalDate.of(2018, Month.APRIL, 23));
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        logger.info(entity.toString());

        entities = em.createNamedQuery(Race.FIND_ALL, Race.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize + 1, entities.size());

        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();

        entities = em.createNamedQuery(Race.FIND_ALL, Race.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());
    }
}
