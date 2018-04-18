package at.racemanager.api.entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
        int countDrivers = team.getDrivers().size();

        team.removeDriver(2l);
        Assert.assertEquals(countDrivers - 1, team.getDrivers().size());
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

        Driver d1 = new Driver();
        d1.setId(team.getDrivers().get(0).getId());
        d1.setFirstname(team.getDrivers().get(0).getFirstname());
        d1.setLastname(team.getDrivers().get(0).getLastname());

        team.addDriver(d1);

        // number of drivers has not changed
        assertEquals(counter, team.getDrivers().size());
    }

}
