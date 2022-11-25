package com.example.trainingproject.service;

import com.example.trainingproject.exception.ResourceNotFoundException;
import com.example.trainingproject.model.Customer;
import com.example.trainingproject.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer){
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByPhone(customer.getPhone());

        if(optionalCustomer.isEmpty()){
            return customerRepository.save(customer);
        }
        throw new ResourceNotFoundException("Customer","Phone Number",customer.getPhone());
    }

    public List<Customer> read(){
        List<Customer> customers = customerRepository.findAll();
        if(customers.isEmpty()){
            throw new RuntimeException("Empty list Found!");
        }
        return customers;
    }

    public Customer update(Customer customer){
        Customer optionalCustomer = customerRepository.findCustomerById(customer.getId())
                .orElseThrow(()->new ResourceNotFoundException("Customer","id",customer.getId()));
        return customerRepository.save(customer);
    }

    public void delete(Long id){
        Customer optionalCustomer = customerRepository.findCustomerById(id)
                .orElseThrow(()->new ResourceNotFoundException("Customer","id",id));

        customerRepository.deleteById(id);
    }

    public Customer getById(Long id){
        return customerRepository.findCustomerById(id)
                .orElseThrow(()->new ResourceNotFoundException("Customer","id",id));
    }
}
