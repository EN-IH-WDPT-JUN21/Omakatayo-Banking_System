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

    List<Account> findByUserLogin(String userLogin);

}
