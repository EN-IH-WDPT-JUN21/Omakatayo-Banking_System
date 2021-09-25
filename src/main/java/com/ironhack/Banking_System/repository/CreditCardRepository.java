package com.ironhack.Banking_System.repository;

import com.ironhack.Banking_System.dao.Checking;
import com.ironhack.Banking_System.dao.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    @Query(value = "SELECT o.id, o.balance, o.primary_owner, o.status, o.creation_date FROM credit_card o", nativeQuery
            = true)
    List<CreditCard> findAllIdBalanceName();
}
