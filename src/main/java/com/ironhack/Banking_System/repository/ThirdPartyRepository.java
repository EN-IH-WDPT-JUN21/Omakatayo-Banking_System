package com.ironhack.Banking_System.repository;

import com.ironhack.Banking_System.dao.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {

    Optional<ThirdParty> findByHashedKey(int hashedKey);
}
