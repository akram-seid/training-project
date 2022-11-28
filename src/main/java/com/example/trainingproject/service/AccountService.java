package com.example.trainingproject.service;


import com.example.trainingproject.dto.AccountResponseDto;
import com.example.trainingproject.model.Account;
import com.example.trainingproject.exception.ResourceExistException;
import com.example.trainingproject.exception.ResourceNotFoundException;
import com.example.trainingproject.model.Customer;
import com.example.trainingproject.repository.AccountRepository;
import com.example.trainingproject.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountService
{
    AccountRepository accountRepository;
    CustomerRepository customerRepository;


    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public Account create(Account account){
        Optional<Account> optionalAccount = accountRepository
                .findAccountByAccountNumber(account.getAccountNumber());
        Optional<Customer> optionalCustomer = customerRepository
                .findCustomerById(account.getCustomer().getId());

        if(optionalAccount.isPresent()){
            throw new ResourceExistException("Account","Account Number",account.getAccountNumber());

        }
        else if(optionalCustomer.isEmpty()){
            throw new ResourceNotFoundException("Customer","Customer ID",account.getCustomer().getId());

        }
        return accountRepository.save(account);

    }

   /* public List<Account> read(){
        List<Account> accounts = accountRepository.findAll();
        if(accounts.isEmpty()){
            throw new RuntimeException("Empty list Found!");
        }
        return accounts;
    }*/

    public List<AccountResponseDto> read(){
        List<AccountResponseDto> accounts = accountRepository.findAccounts();
        if(accounts.isEmpty()){
            throw new RuntimeException("Empty list Found!");
        }
        return accounts;
    }

    public void delete(Long id){
        Account optionalAccount = accountRepository.findAccountById(id)
                .orElseThrow(()->new ResourceNotFoundException("Account","id",id));

        accountRepository.deleteById(id);
    }

    public List<Account> getByCustomerId(Long id){
        List<Account> Accounts = accountRepository.findAccountByCustomerId(id);
        if(Accounts.isEmpty()){
            throw new ResourceNotFoundException("Accounts","Customer Id",id);
        }
        return Accounts;
    }



}
