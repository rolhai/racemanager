/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.racemanager.data.store;

import at.racemanager.api.entity.RmException;
import at.racemanager.api.entity.RmParameterException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rolhai
 */
public abstract class DataStore<E> {

    protected Class<E> entityClass;

    @PersistenceContext
    protected EntityManager em;

    @PostConstruct
    public void init() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    public E create(final E entity) throws RmException {
        if (entity == null) {
            throw new RmParameterException(entityClass.getSimpleName(), "not provided");
        }
        em.persist(entity);
        return entity;
    }

    public E update(final E entity) throws RmException {
        if (entity == null) {
            throw new RmParameterException(entityClass.getSimpleName(), "not provided");
        }
        return em.merge(entity);
    }

    public void removeById(final Long id) throws RmException {
        if (id <= 0) {
            throw new RmParameterException("id", "is invalid");
        }
        remove(findById(id));
    }

    public void remove(final E entity) throws RmException {
        if (entity == null) {
            throw new RmParameterException(entityClass.getSimpleName(), "not provided");
        }
        em.remove(em.merge(entity));
    }

    public E findById(final Long id) throws RmException {
        if (id <= 0) {
            throw new RmParameterException("id", "is invalid");
        }
        return em.find(entityClass, id);
    }

    public List<E> findAll() {
        return em.createNamedQuery(getFindAllNamedQuery()).getResultList();
    }

    public Long countResults() {
        return (Long) em.createNamedQuery(getCountResultNamedQuery()).getSingleResult();
    }

    protected abstract String getCountResultNamedQuery();

    protected abstract String getFindAllNamedQuery();
}
