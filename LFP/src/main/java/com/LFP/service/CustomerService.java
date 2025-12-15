package com.LFP.service;

import com.LFP.model.customer;
import com.LFP.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public customer save(customer customer){
        return customerRepository.save(customer);
    }

    public List<customer> getCustomers(){
        return customerRepository.findAll();
    }

    public customer getByName(String name){
        return customerRepository.findByname(name);
    }

    public customer getById(Long id){
        return customerRepository.getReferenceById(id);
    }

    public customer getByCustomerNo(String custNo){
        return customerRepository.findByCustomerNO(custNo);
    }
}
