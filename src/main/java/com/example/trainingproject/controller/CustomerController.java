package com.example.trainingproject.controller;

import com.example.trainingproject.model.Customer;
import com.example.trainingproject.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody Customer customer){
        customerService.create(customer);
                return new  ResponseEntity<>("saved Successfully",HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<?> findAllCustomers(){
        return new ResponseEntity<>(customerService.read(),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.update(customer), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id){
        customerService.delete(id);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> findCustomer(@PathVariable("id") Long customerId){

        return new ResponseEntity<>(customerService.getById(customerId), HttpStatus.OK);
    }
}
