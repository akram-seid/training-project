package com.example.trainingproject.repository;

import com.example.trainingproject.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByAccountNumber(BigInteger accountNumber);

    Optional<Account> findAccountById(Long id);

    List<Account> findAccountByCustomerId(Long id);
}
