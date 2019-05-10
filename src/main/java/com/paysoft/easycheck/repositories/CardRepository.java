package com.paysoft.easycheck.repositories;

import com.paysoft.easycheck.models.Card;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class CardRepository extends AbstractRepository<Card> {

    @PersistenceContext(unitName = "easyCheckPU")
    EntityManager entityManager;

    public CardRepository() {
        super(Card.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
