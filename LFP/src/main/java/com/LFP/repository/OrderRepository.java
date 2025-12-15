package com.LFP.repository;

import com.LFP.model.order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface OrderRepository extends JpaRepository<order,Long> {
    order getOrderByOrderNo(String orderNo);
    order getOrderBydate(LocalDate date);
}
