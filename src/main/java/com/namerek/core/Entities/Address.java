package com.namerek.core.Entities;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private long id;
    @Column
    private String country;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String building;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "is_post_office")
    private boolean isPostOffice;

}
