package com.vending.machine.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vending.product.model.Coin;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

}
