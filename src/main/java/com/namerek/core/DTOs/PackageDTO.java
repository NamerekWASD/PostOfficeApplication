package com.namerek.core.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDTO {

    private long id;

    private double weight;

    private String size;

    private String category;

    private String content;

    private String description;

    private OrderDTO order;

    private double evaluativeCost;

}
