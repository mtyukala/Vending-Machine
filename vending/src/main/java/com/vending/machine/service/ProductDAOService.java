package com.vending.machine.service;

import com.vending.machine.model.Product;

import javax.persistence.EntityManager;

//@Repository
//@Transactional
public class ProductDAOService {
//    @PersistenceContext
    private EntityManager entityManager;

    public long insert(Product product){
        entityManager.persist(product);
        return product.getId();
    }

}
