package com.namerek.core.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class LightOrderDTO {
    private long trackNumber;

    private long sender;

    private long receiver;

    private String  departurePoint;

    private String destination;

    private String status;

    private Date dateOfCreation;

    private String cell;

    public LightOrderDTO(long trackNumber, long sender, long receiver, String departurePoint, String destination, String status, Date dateOfCreation) {
        this.trackNumber = trackNumber;
        this.sender = sender;
        this.receiver = receiver;
        this.departurePoint = departurePoint;
        this.destination = destination;
        this.status = status;
        this.dateOfCreation = dateOfCreation;
    }
}
