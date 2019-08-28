package com.vending.machine.repository;

import com.vending.machine.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

    @Modifying
    @Query("UPDATE Coin c SET c.count=:count WHERE c.cid=:id")
    public void updateCoinCount(@Param("id") Long id, @Param("count") int count);
}
