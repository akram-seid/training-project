package com.example.trainingproject.controller;

import com.example.trainingproject.model.Transaction;
import com.example.trainingproject.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> savetransaction(@Validated @RequestBody Transaction transaction){
        transactionService.create(transaction);
        return new  ResponseEntity<>("saved Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<?> findAlltransactions(){
        return new ResponseEntity<>(transactionService.read(),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> findTransaction(@PathVariable("id")BigInteger accountNumber){

        return new ResponseEntity<>(transactionService.readByAccount(accountNumber), HttpStatus.OK);
    }
}
