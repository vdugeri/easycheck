package com.paysoft.easycheck.repositories;

import com.paysoft.easycheck.models.Merchant;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class MerchantRepository extends AbstractRepository<Merchant> {

    @PersistenceContext(unitName = "easyCheckPU")
    EntityManager entityManager;

    public MerchantRepository() {
        super(Merchant.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
