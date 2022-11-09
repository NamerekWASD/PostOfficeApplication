package com.namerek.core.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LightOrderModel {
    private long trackNumber;

    private long sender;

    private long receiver;

    private String departurePoint;

    private String destination;

    private String status;

    private Date dateOfCreation;

    private String cell;
}
