package com.LFP.service;

import com.LFP.model.order;
import com.LFP.model.product;
import com.LFP.repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;
@Service

public class ProductService {

    private final ProductRepository productRepository;
    private final OrderService orderService;

    public ProductService(ProductRepository productRepository, OrderService orderService) {
        this.productRepository = productRepository;
        this.orderService = orderService;
    }

    public product save(product product){
        return productRepository.save(product);
    }

    public product getByName(String name){
        return productRepository.getProductByName(name);
    }
    //get all the products
    public List<product> getProducts(){
        return productRepository.findAll();
    }

    public product getProdByProdId(String ProdId){
        return productRepository.getProductByProductID(ProdId);
    }

    public product getProdById(Long id){
        return productRepository.getReferenceById(id);
    }

    //calculate total from the products
    public double revenue(){
        List<order> orders = orderService.getAllOrders();
        double total =0;

        for(order x : orders){
            if(x != null && getByName(x.getProductName()) != null){
                total+= getByName(x.getProductName()).getPrice() * x.getQuantity();
            }
            else{
                total +=0;
            }

        }

        return total;
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }
}
