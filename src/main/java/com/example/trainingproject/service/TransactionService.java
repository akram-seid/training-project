package com.example.trainingproject.service;

import com.example.trainingproject.model.Account;
import com.example.trainingproject.model.Transaction;
import com.example.trainingproject.repository.AccountRepository;
import com.example.trainingproject.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    TransactionRepository transactionRepository;
    AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }


    public Transaction create(Transaction transaction) {
        Optional<Account> account = accountRepository
                .findAccountByAccountNumber(transaction.getAccount().getAccountNumber());
        if (account.isPresent()) {
            transactionRepository.save(transaction);

        }
        throw new RuntimeException("Account number is not found!");
    }
    public List<Transaction> read(){
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions.isEmpty()){
            throw new RuntimeException("There are not transactions!");
        }
        return transactions;
    }

    public List<Transaction> readByAccount(BigInteger accountNumber){
        List<Transaction> transactions = transactionRepository.findTransactionByAccount_AccountNumber(accountNumber);
        if (transactions.isEmpty()){
            throw new RuntimeException("There are not transactions!");
        }
        return transactions;
    }




}