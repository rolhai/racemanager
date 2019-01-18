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
import java.util.List;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for CountryStore
 *
 * @author rolhai
 */
public class CountryStoreTest extends StoreTest {

    private static final Logger logger = Logger.getLogger(CountryStoreTest.class.getName());

    @Test
    public void testCountries() {
        int initSize = 8;

        List<Country> entities = em.createNamedQuery(Country.FIND_ALL, Country.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());

        Country entity = new Country();
        entity.setIsoCode("SE");
        entity.setName("Sweden");

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getId());
        logger.info(entity.toString());

        entities = em.createNamedQuery(Country.FIND_ALL, Country.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize + 1, entities.size());

        entity.setIsoCode("SW");
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        logger.info(entity.toString());

        entities = em.createNamedQuery(Country.FIND_ALL, Country.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize + 1, entities.size());

        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();

        entities = em.createNamedQuery(Country.FIND_ALL, Country.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());
    }
}
