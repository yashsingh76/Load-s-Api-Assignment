package com.LiveasyLogistics.Load.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "load_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Load {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String loadingPoint;

    @Column(nullable = false)
    private String unloadingPoint;

    @Column(nullable = false)
    private String productType;

    @Column(nullable = false)
    private String truckType;

    @Column(nullable = false)
    private Integer noOfTrucks;

    @Column(nullable = false)
    private Double weight;


    private String comment;

    @Column()
    private String shipperId;

    @Column()
    private String date;

}
