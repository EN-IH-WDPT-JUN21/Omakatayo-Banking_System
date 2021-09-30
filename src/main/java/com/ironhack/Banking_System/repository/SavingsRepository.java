package com.ironhack.Banking_System.repository;

import com.ironhack.Banking_System.dao.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Long> {

    List<Savings> findByUserLogin(String userLogin);
}
