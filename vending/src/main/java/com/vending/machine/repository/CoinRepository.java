package com.vending.machine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vending.machine.model.Coin;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

}
