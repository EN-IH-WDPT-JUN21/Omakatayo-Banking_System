package com.ironhack.Banking_System.repository;

import com.ironhack.Banking_System.dao.Account;
import com.ironhack.Banking_System.dao.AccountHolder;
import com.ironhack.Banking_System.dao.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByPrimaryOwnerId(Long id);

    /*@Query("SELECT id, balance, primary_owner, status, creation_date FROM Checking")
    Optional<Account> findByIdAndPrimaryOwner(Long id, String primaryOwnerName);*/

    /*@Query("SELECT a.account FROM Account a WHERE a.id = :id AND a.")
    Optional<Account> findByIdAndSecondaryOwner (Long id, String secondaryOwnerName);*/
}
