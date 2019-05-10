package com.paysoft.easycheck.repositories;

import com.paysoft.easycheck.models.Transaction;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class TransactionRepository extends AbstractRepository<Transaction> {

    @PersistenceContext(unitName = "easyCheckPU")
    private EntityManager entityManager;

    public TransactionRepository() {
        super(Transaction.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
