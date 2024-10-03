package com.LiveasyLogistics.Load.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLoadDto {

    private int id;

    private String loadingPoint;

    private String unloadingPoint;

    private String productType;

    private String truckType;

    private Integer noOfTrucks;

    private Double weight;

    private String comment;

    private String date;

}
