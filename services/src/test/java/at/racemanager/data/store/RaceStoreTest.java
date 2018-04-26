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

import at.racemanager.api.entity.Race;
import at.racemanager.api.entity.Track;
import static at.racemanager.data.store.StoreTest.em;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

/**
 *
 * @author rolhai
 */
public class RaceStoreTest extends StoreTest {

    private static final Logger logger = LogManager.getLogger(RaceStoreTest.class);

    //@Test
    public void testRaces() {
        int initSize = 0;

        List<Race> entities = em.createQuery("FROM Race", Race.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());

        Race entity = new Race();
        entity.setRaceDate(LocalDate.of(2018, Month.MARCH, 17));
        Track track = em.createQuery("SELECT t FROM Track t WHERE t.name = :name", Track.class)
                .setParameter("name", "Monza")
                .getSingleResult();
        entity.setTrack(track);

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getId());
        logger.info(entity.toString());

        entities = em.createQuery("FROM Race", Race.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize + 1, entities.size());

        entity.setRaceDate(LocalDate.of(2018, Month.APRIL, 23));
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        logger.info(entity.toString());

        entities = em.createQuery("FROM Race", Race.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize + 1, entities.size());

        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();

        entities = em.createQuery("FROM Race", Race.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());
    }
}
