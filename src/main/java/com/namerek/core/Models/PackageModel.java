package com.namerek.core.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageModel {

    private long id;

    private double weight;

    private String size;

    private String category;

    private String content;

    private String description;

    private OrderModel order;

    private Date dateOfDeparture;

    private double evaluativeCost;
}
