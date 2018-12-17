package com.paysoft.easycheck.repositories;

import com.paysoft.easycheck.models.Admin;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class AdminRepository extends AbstractRepository<Admin> {

    @PersistenceContext(unitName = "easyCheckPU")
    private EntityManager entityManager;

    public AdminRepository() {
        super(Admin.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
