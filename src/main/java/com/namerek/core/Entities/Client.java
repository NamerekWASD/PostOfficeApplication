package com.namerek.core.Entities;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "Client")
public class Client {
    @Id
    @Column(name = "phone_number")
    private long phoneNumber;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "personal_status")
    private String personalStatus;

    @Column
    private long EDRPOU;
}
