package com.ironhack.Banking_System.repository;

import com.ironhack.Banking_System.dao.Checking;
import com.ironhack.Banking_System.dao.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Long> {

    @Query(value = "SELECT o.id, o.balance, o.primary_owner, o.status, o.creation_date FROM Savings o", nativeQuery =
            true)
    List<Savings> findAllIdBalanceName();
}
