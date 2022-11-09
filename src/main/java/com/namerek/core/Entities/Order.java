package com.namerek.core.Entities;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Order_")
public class Order {
    @Id
    @Column(name = "track_number")
    private long trackNumber;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sender")
    private Client sender;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver")
    private Client receiver;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_point", referencedColumnName = "address_id")
    private Address departurePoint;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "destination", referencedColumnName = "address_id")
    private Address destination;

    @Column
    private String status;

    @Column
    private double cost;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cell_id")
    private Cell cell;
}
