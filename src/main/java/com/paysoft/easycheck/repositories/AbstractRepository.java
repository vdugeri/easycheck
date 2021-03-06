package com.paysoft.easycheck.repositories;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Stateless
@LocalBean
public abstract class AbstractRepository<T> {
    private Class<T> entityClass;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @return the entity manager for the entity in concern
     */
    protected abstract EntityManager getEntityManager();


    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param entity
     *
     * @return created entity
     */
    public T create(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();

        return entity;
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param entity to edit
     *
     * @return edited entity
     */
    public T edit(T entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();

        return entity;
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param ID of the entity to delete
     */
    public void delete(Object ID) {
        Object ref = getEntityManager().getReference(entityClass, ID);
        getEntityManager().remove(ref);
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param entity to remove
     * @return the removed entity
     */
    public T remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();

        return entity;
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param ID to find
     * @return the entity matching the specified ID
     */
    public T find(Object ID) {
        return getEntityManager().find(entityClass, ID);
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @return all records in the data store
     */
    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));

        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param range of records to find
     * @return the records found
     */
    public List<T> findRange(int[] range) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query query = getEntityManager().createQuery(cq);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);

        return query.getResultList();
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param limit of results to return
     * @param offset first result to return
     *
     * @return list of records
     */
    public List<T> findWithLimitAndOffset(int limit, int offset) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query query = getEntityManager().createQuery(cq);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @return the total number of records in the data store
     */
    public int count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> root = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(root));
        Query query = getEntityManager().createQuery(cq);

        return ((Long) query.getSingleResult()).intValue();
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param namedQuery name of query
     *
     * @return the result of the named query
     */
    public List<T> findWithNamedQuery(String namedQuery) {
        return getEntityManager().createNamedQuery(namedQuery).getResultList();
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     * @param namedQuery name of query
     * @param parameters dynamic query params
     * @param limit of results to return
     *
     * @return
     */
    public List<T> findWithNamedQuery(String namedQuery, Map<String, Object> parameters, int limit) {
        Set<Map.Entry<String, Object>> rawParams = parameters.entrySet();
        Query query = getEntityManager().createNamedQuery(namedQuery);

        if (limit > 0) {
            query.setMaxResults(limit);
        }

        for (Map.Entry<String, Object> entry : rawParams) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param queryName name of query
     *
     * @return query results
     */
    public T findSingleWithNamedQuery(String queryName) {
        try {
            return (T) getEntityManager().createNamedQuery(queryName).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param queryName name of query
     * @param parameters dynamic query results
     *
     * @return result of query
     */
    public T findSingleWithNamedQuery(String queryName, Map<String, Object> parameters) {
        Set<Map.Entry<String, Object>> rawParams = parameters.entrySet();
        Query query = getEntityManager().createNamedQuery(queryName);

        for (Map.Entry<String, Object> entry: rawParams) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        try {
            return (T) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param queryName
     * @param parameters
     * @param limit
     * @param offset
     *
     * @return List of {@link T}
     */
    public List<T> findWithLimitOffsetNamedQuery(String queryName, Map<String, Object> parameters, int limit, int offset) {
        Set<Map.Entry<String, Object>> rawParams = parameters.entrySet();
        Query query = getEntityManager().createNamedQuery(queryName);

        for (Map.Entry<String, Object> entry : rawParams) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        query.setFirstResult(offset);
        query.setMaxResults(limit);

        try {
            return (List<T>) query.getResultList();
        } catch ( NoResultException e) {
            return Collections.emptyList();
        }
    }
}
