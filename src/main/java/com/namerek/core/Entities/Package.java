package com.namerek.core.Entities;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Package")
public class Package {

    @Id
    @Column(name = "package_id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private long id;

    @Column
    private double weight;

    @Column(name = "size_")
    private String size;

    @Column
    private String category;

    @Column
    private String content;

    @Column
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "track_number", referencedColumnName = "track_number")
    private Order order;

    @Column(name = "date_of_departure")
    private Date dateOfDeparture;

    @Column(name = "EVALUATIVE_COST")
    private double evaluativeCost;
}
