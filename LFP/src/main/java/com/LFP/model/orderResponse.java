package com.LFP.model;

import java.util.List;

public class orderResponse {
    private Long id;
    private String orderNo;
    private double quantity;
    private List<product> productList;

    public orderResponse() {
    }

    public orderResponse(Long id, String orderNo, double quantity) {
        this.id = id;
        this.orderNo = orderNo;
        this.quantity = quantity;
    }

    public orderResponse(Long id, String orderNo, double quantity, List<product> productList) {
        this.id = id;
        this.orderNo = orderNo;
        this.quantity = quantity;
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public List<product> getProductList() {
        return productList;
    }

    public void setProductList(List<product> productList) {
        this.productList = productList;
    }
}
