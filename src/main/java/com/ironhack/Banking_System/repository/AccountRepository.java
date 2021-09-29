package com.ironhack.Banking_System.repository;

import com.ironhack.Banking_System.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByIdAndPrimaryOwnerId(Long id, Optional<Long> primaryOwnerId);

    Optional<Account> findByIdAndSecondaryOwnerId(Long id, Optional<Long> secondaryOwnerId);

    Optional<Account> findByIdAndSecretKey(Long id, String secretKey);

    /*@Query("SELECT id, balance, primary_owner, status, creation_date FROM Checking")
    Optional<Account> findByIdAndPrimaryOwner(Long id, String primaryOwnerName);*/

    /*@Query("SELECT a.account FROM Account a WHERE a.id = :id AND a.")
    Optional<Account> findByIdAndSecondaryOwner (Long id, String secondaryOwnerName);*/
}
