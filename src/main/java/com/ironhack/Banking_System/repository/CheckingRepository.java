package com.ironhack.Banking_System.repository;

import com.ironhack.Banking_System.dao.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {

    Optional<Checking> findById(Long id);

    @Query(value = "SELECT id, balance, primary_owner, status, creation_date FROM Checking", nativeQuery =
            true)
    List<Checking> findAllIdBalanceName();

    List<Checking> findByUserLogin(String userLogin);
}

