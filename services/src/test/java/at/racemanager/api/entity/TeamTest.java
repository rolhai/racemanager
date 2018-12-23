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
package at.racemanager.api.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TeamTest {

    private static TestdataBuilder testdataBuilder;

    public TeamTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testdataBuilder = new TestdataBuilder();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * remove driver from team
     */
    @Test
    public void removeDrivers() {
        Team team = new Team();
        team.setDrivers(testdataBuilder.getDrivers());
        assertNotNull(team.getDrivers());

        int initSize = team.getDrivers().size();
        team.removeDriver(2l);
        int afterRemoveSize = team.getDrivers().size();

        Assert.assertEquals(initSize - 1, afterRemoveSize);
    }

    /**
     * remove driver from team with no drivers
     */
    @Test
    public void removeDriverFromEmptyList() {
        Team team = new Team();
        assertNull(team.getDrivers());
        team.removeDriver(1l);
    }

    /**
     * add driver to team with no drivers
     */
    @Test
    public void addDriverToEmptyList() {
        Team team = new Team();

        Driver d1 = new Driver();
        d1.setId(1l);
        d1.setFirstname("Micky");
        d1.setLastname("Mouse");

        assertNull(team.getDrivers());
        team.addDriver(d1);
        assertNotNull(team.getDrivers());
        assertEquals(1, team.getDrivers().size());
    }

    /**
     * adding existing drivers is not possible
     */
    @Test
    public void addExistingDriver() {
        Team team = new Team();
        team.setDrivers(testdataBuilder.getDrivers());

        assertNotNull(team.getDrivers());
        int counter = team.getDrivers().size();

        Driver existingDriver = team.getDrivers().stream().findAny().get();
        Driver d1 = new Driver();
        d1.setId(existingDriver.getId());
        d1.setFirstname(existingDriver.getFirstname());
        d1.setLastname(existingDriver.getLastname());

        team.addDriver(d1);

        // number of drivers has not changed
        assertEquals(counter, team.getDrivers().size());
    }

}
