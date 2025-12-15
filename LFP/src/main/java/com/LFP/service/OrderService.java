package com.LFP.service;

import com.LFP.model.order;
import com.LFP.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository,
                        @Lazy ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public order Save(order order){
        order.setTotalPrice( order.getQuantity() * productService.getByName(order.getProductName()).getPrice() );
        return orderRepository.save(order);
    }

    public void delete(long id){
        orderRepository.deleteById(id);
    }

    public order getOrderByID(Long id){
        return orderRepository.getReferenceById(id);
    }

    public order getOrderByOrderNo(String orderNo){
        return orderRepository.getOrderByOrderNo(orderNo);
    }

    public order getOrderByDate(LocalDate date){
        return orderRepository.getOrderBydate(date);
    }

    public List<order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<order> getTodayOrders(){
        List<order> today = new ArrayList<>();
        for(order x : getAllOrders()){
            if (x.getDate().toString().equals(LocalDate.now().toString())){
                today.add(x);
            }
        }

        return today;
    }

    public int getTotalQuantity(){
        int total = 0;
        for(order x : getAllOrders()){
            total += x.getQuantity();
        }

        return total;
    }

    public int getThisMonthOrderCount(){
        int total =0;
        for(order x : getAllOrders()){
            if(x.getDate().getMonth().toString().equals( LocalDate.now().getMonth().toString() )){
                total++;
            }
        }

        return total;
    }

    public void update(order order){
        order.setTotalPrice( order.getQuantity() * productService.getByName(order.getProductName()).getPrice() );
        orderRepository.save(order);
    }
}
