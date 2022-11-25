package com.example.trainingproject.repository;


import com.example.trainingproject.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionByAccount_AccountNumber(BigInteger accountNUmber);
}
