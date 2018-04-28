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
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rolhai
 */
public class DriverStoreTest extends StoreTest {

    private static final Logger logger = LogManager.getLogger(DriverStoreTest.class);

    @Test
    public void testDrivers() {
        int initSize = 5;

        List<Driver> entities = em.createQuery("FROM Driver", Driver.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());

        Driver entity = new Driver();
        entity.setFirstname("Alexander");
        entity.setLastname("Wurz");
        Country country = em.createQuery("SELECT c FROM Country c WHERE c.isoCode = :isoCode", Country.class)
                .setParameter("isoCode", "AT")
                .getSingleResult();
        entity.setCountry(country);

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getId());
        logger.info(entity.toString());

        entities = em.createQuery("FROM Driver", Driver.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize + 1, entities.size());

        entity.setFirstname("Alex");
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        logger.info(entity.toString());

        entities = em.createQuery("FROM Driver", Driver.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize + 1, entities.size());

        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();

        entities = em.createQuery("FROM Driver", Driver.class).getResultList();
        Assert.assertNotNull(entities);
        Assert.assertEquals(initSize, entities.size());
    }

}
