package com.vending.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vending.product.model.Product;

/**
 * Defines an interface with methods to access products from the database
 * 
 * @author Mkhululi Tyukala
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	// List<Product> findByProductId(Long id);
}
