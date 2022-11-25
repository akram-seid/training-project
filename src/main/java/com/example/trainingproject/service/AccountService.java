package com.example.trainingproject.service;


import com.example.trainingproject.exception.ResourceExistException;
import com.example.trainingproject.exception.ResourceNotFoundException;
import com.example.trainingproject.model.Account;
import com.example.trainingproject.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountService extends ParentService
{
    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account create(Account account){
        Optional<Account> optionalAccount = accountRepository
                .findAccountByAccountNumber(account.getAccountNumber());

        if(optionalAccount.isPresent()){
            throw new ResourceExistException("Account","Account Number",account.getAccountNumber());
        }
        return accountRepository.save(account);

    }

    public List<Account> read(){
        List<Account> Accounts = accountRepository.findAll();
        if(Accounts.isEmpty()){
            throw new RuntimeException("Empty list Found!");
        }
        return Accounts;
    }
    public void delete(Long id){
        Account optionalAccount = accountRepository.findAccountById(id)
                .orElseThrow(()->new ResourceNotFoundException("Account","id",id));

        accountRepository.deleteById(optionalAccount.getId());
    }

    public List<Account> getByCustomerId(Long id){
        List<Account> Accounts = accountRepository.findAccountByCustomerId(id);
        if(Accounts.isEmpty()){
            throw new ResourceNotFoundException("Accounts","Customer Id",id);
        }
        return Accounts;
    }
}
