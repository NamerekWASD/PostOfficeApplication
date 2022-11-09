package com.namerek.core.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private long trackNumber;

    private ClientDTO sender;

    private ClientDTO receiver;

    private AddressDTO departurePoint;

    private AddressDTO destination;

    private String status;

    private double cost;

    private Date dateOfCreation;

    private CellDTO cell;

    public double getBaseCost() {
        return 40;
    }
}
