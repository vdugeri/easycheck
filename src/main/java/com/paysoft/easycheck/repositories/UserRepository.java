package com.paysoft.easycheck.repositories;

import com.paysoft.easycheck.models.Customer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UserRepository extends AbstractRepository<Customer> {

    @PersistenceContext(unitName = "easyCheckPU")
    private EntityManager entityManager;

    public UserRepository() {
        super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
