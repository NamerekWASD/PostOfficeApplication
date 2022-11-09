package com.namerek.core.Entities;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "phone_number")
    private long phone_number;
    @Column
    private String password;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @Column(name = "date_of_join")
    private Date dateOfJoin;

    @Column
    private String qualification;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_office_number")
    private PostOffice postOffice;
}
