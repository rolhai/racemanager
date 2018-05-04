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

import at.racemanager.api.entity.Championship;
import at.racemanager.api.entity.Race;
import at.racemanager.api.entity.Team;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import static at.racemanager.data.store.StoreTest.em;

/**
 * Tests for ChampionshipStore
 *
 * @author rolhai
 */
public class ChampionshipStoreTest extends StoreTest {

    private static final Logger logger = LogManager.getLogger(ChampionshipStoreTest.class);

    @Test
    public void testChampionships() {
        int initSize = 0;

        List<Championship> entities = em.createNamedQuery(Championship.FIND_ALL, Championship.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());

        Championship entity = new Championship();
        entity.setTemplate(true);
        entity.setYear(2018);
        entity.setStartDate(LocalDate.of(2018, Month.MARCH, 17));

        List<Team> teams = em.createNamedQuery(Team.FIND_ALL, Team.class).getResultList();
        entity.setTeams(teams);

        List<Race> races = em.createNamedQuery(Race.FIND_ALL, Race.class).getResultList();
        entity.setRaces(races);

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getId());
        logger.info(entity.toString());

        entities = em.createNamedQuery(Championship.FIND_ALL, Championship.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertNotNull(entities.get(0).getTeams());
        Assert.assertNotNull(entities.get(0).getRaces());
        Assert.assertEquals(initSize + 1, entities.size());

        entity.setEndDate(LocalDate.of(2018, Month.NOVEMBER, 23));
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        logger.info(entity.toString());

        entities = em.createNamedQuery(Championship.FIND_ALL, Championship.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertNotNull(entities.get(0).getStartDate());
        Assert.assertNotNull(entities.get(0).getEndDate());
        Assert.assertNotNull(entities.get(0).getTeams());
        Assert.assertNotNull(entities.get(0).getRaces());
        Assert.assertEquals(initSize + 1, entities.size());

        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();

        entities = em.createNamedQuery(Championship.FIND_ALL, Championship.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());
    }
}
