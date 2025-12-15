package com.LFP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor

@Entity
@Table(name = "orders")
public class order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String orderNo;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String orderedBy;

    public order() {
    }

    public order(Long id, String ordernumber, int quantity) {
        this.id = id;
        this.orderNo = ordernumber;
        this.quantity = quantity;
    }

    @PrePersist
    protected void onCreate(){
        date = LocalDate.now();
    }
}
