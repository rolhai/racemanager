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
import at.racemanager.api.entity.Track;
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
public class TrackStoreTest extends StoreTest {

    private static final Logger logger = LogManager.getLogger(TrackStoreTest.class);

    @Test
    public void testTracks() {
        int initSize = 3;

        List<Track> entities = em.createQuery("FROM Track", Track.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());

        Track entity = new Track();
        entity.setName("Silverstone");
        Country country = em.createQuery("SELECT c FROM Country c WHERE c.isoCode = :isoCode", Country.class)
                .setParameter("isoCode", "GB")
                .getSingleResult();
        entity.setCountry(country);

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getId());
        logger.info(entity.toString());

        entities = em.createQuery("FROM Track", Track.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize + 1, entities.size());

        entity.setName("Goldstone");
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        logger.info(entity.toString());

        entities = em.createQuery("FROM Track", Track.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize + 1, entities.size());

        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();

        entities = em.createQuery("FROM Track", Track.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());
    }
}
