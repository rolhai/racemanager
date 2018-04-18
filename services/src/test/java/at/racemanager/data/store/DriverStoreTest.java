/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.data.store;

import at.racemanager.api.entity.Driver;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rolhai
 */
public class DriverStoreTest extends StoreTest {

    @Test
    public void getDriverById() {
        Driver driver = em.find(Driver.class, 1L);
        Assert.assertNotNull(driver);
    }

    @Test
    public void getAllDrivers() {
        List<Driver> drivers = em.createQuery("FROM Driver", Driver.class).getResultList();
        Assert.assertNotNull(drivers);
        Assert.assertEquals(3, drivers.size());

        Driver d1 = drivers.get(0);
        Assert.assertNotNull(d1.getCountry());
    }

    @Test
    public void countAllDrivers() {
        long count = (Long) em.createQuery("SELECT COUNT(d) FROM Driver d").getSingleResult();
        Assert.assertEquals(3, count);
    }
}
