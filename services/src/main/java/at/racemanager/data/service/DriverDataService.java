/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.data.service;

import at.racemanager.api.model.Driver;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author rolhai
 */
@Stateless
public class DriverDataService {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public Driver createDriver(Driver driver) {
        return em.merge(driver);
    }

    public void saveDriver(Driver driver) {
        em.persist(driver);
    }

    public Driver findDriver(Long id) {
        return em.find(Driver.class, id);
    }

    public List<Driver> getDrivers() {
        TypedQuery<Driver> query = em.createNamedQuery("Driver.selectAll", Driver.class);
        return query.getResultList();

        /*
        List<Driver> drivers = new ArrayList<>();

        Country spain = new Country();
        spain.setIsoCode("ES");
        spain.setName("Spain");

        Country germany = new Country();
        germany.setIsoCode("DE");
        germany.setName("Germany");

        Country greatBritain = new Country();
        greatBritain.setIsoCode("GB");
        greatBritain.setName("United Kingdom of Great Britain and Northern Ireland");

        Driver alonso = new Driver();
        alonso.setFirstname("Fernando");
        alonso.setLastname("Alonso");
        alonso.setDateOfBirth(LocalDate.of(1981, 7, 29));
        alonso.setCountry(spain);

        Driver vettel = new Driver();
        vettel.setFirstname("Sebastian");
        vettel.setLastname("Vettel");
        vettel.setDateOfBirth(LocalDate.of(1987, 7, 3));
        vettel.setCountry(germany);

        Driver hamilton = new Driver();
        hamilton.setFirstname("Lewis");
        hamilton.setLastname("Hamilton");
        hamilton.setDateOfBirth(LocalDate.of(1985, 1, 7));
        hamilton.setCountry(greatBritain);

        drivers.add(alonso);
        drivers.add(vettel);
        drivers.add(hamilton);
        return drivers;
         */
    }
}
