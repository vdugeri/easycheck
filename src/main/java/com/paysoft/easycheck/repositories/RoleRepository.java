package com.paysoft.easycheck.repositories;

import com.paysoft.easycheck.models.Role;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class RoleRepository extends AbstractRepository<Role> {

    @PersistenceContext(unitName = "easyCheckPU")
    EntityManager entityManager;

    public RoleRepository() {
        super(Role.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
