package com.namerek.core.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    private long trackNumber;

    private ClientModel sender;

    private ClientModel receiver;

    private AddressModel departurePoint;

    private AddressModel destination;

    private String status;

    private double cost;

    private Date dateOfCreation;

    private CellModel cell;

    public void setSenderPhone(long phoneNumber){
        sender.setPhoneNumber(phoneNumber);
    }
    public void setReceiverPhone(long phoneNumber){
        receiver.setPhoneNumber(phoneNumber);
    }
    public void setDeparturePointId(long id){
        departurePoint.setId(id);
    }
    public void setDestinationId(long id){
        destination.setId(id);
    }
}
