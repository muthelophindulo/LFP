package com.LFP.repository;

import com.LFP.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<customer,Long> {
    customer findByname(String name);

    customer findByCustomerNO(String customerNo);
}
