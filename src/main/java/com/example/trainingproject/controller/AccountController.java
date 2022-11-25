package com.example.trainingproject.controller;

import com.example.trainingproject.model.Account;
import com.example.trainingproject.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {
    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> saveAccount(@Valid @RequestBody Account account){
        accountService.create(account);
        return new  ResponseEntity<>("saved Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<?> findAllAccounts(){
        return new ResponseEntity<>(accountService.read(),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id){
        accountService.delete(id);
        return new ResponseEntity<>("Data Deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> findAccount(@PathVariable("id") Long accountId){

        return new ResponseEntity<>(accountService.getByCustomerId(accountId), HttpStatus.OK);
    }
}
