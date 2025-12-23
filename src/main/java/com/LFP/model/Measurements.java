package com.LFP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Measurements")
@Builder
public class Measurements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column(name = "measurement_date")
    private LocalDateTime measurementDate;

    @Column(name = "bust")
    private Double bust;

    @Column(name = "hips")
    private Double hips;

    @Column(name = "shoulder")
    private Double shoulder;

    @Column(name = "thigh")
    private Double thigh;

    @Column(name = "sleeves")
    private Double sleeves;


    @Column(name = "top_length")
    private Double topLength;

    @Column(name = "bottom_length")
    private Double bottomLength;

    @Column(name = "bottom_waist")
    private Double bottomWaist;


    @Column(name = "inseam")
    private Double inseam;


    @PrePersist
    protected void onCreate() {
        if (measurementDate == null) {
            measurementDate = LocalDateTime.now();
        }
    }
}