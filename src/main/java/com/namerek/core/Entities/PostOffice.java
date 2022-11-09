package com.namerek.core.Entities;


import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Post_Office")
public class PostOffice {
    @Id
    @Column(name = "post_office_number")
    private long officeNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "post_type")
    private String postType;
}
