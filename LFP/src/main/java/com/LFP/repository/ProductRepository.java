package com.LFP.repository;

import com.LFP.model.product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<product,Long> {
    product getProductByName(String name);
    product getProductByProductID(String ProdID);
}
