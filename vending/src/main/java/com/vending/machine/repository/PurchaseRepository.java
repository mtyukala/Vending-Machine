package com.vending.machine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vending.machine.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
