package com.paysoft.easycheck.repositories;

import com.paysoft.easycheck.models.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UserRepository extends AbstractRepository<User> {

    @PersistenceContext(unitName = "easyCheckPU")
    private EntityManager entityManager;

    public UserRepository() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
