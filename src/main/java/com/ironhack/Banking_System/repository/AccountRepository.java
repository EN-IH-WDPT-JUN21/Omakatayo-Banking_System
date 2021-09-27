package com.ironhack.Banking_System.repository;

import com.ironhack.Banking_System.dao.Account;
import com.ironhack.Banking_System.dao.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByPrimaryOwnerId(Long id);
}
