package com.ironhack.Banking_System.repository;

import com.ironhack.Banking_System.dao.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {

    Optional<Checking> findById(Long id);
}
