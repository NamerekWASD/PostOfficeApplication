package com.namerek.core.Entities;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "cell")
public class Cell {
    @Id
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    @Column(name = "cell_id")
    private long id;

    @Column(name = "unique_code")
    private String uniqueCode;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_office_number", referencedColumnName = "post_office_number")
    @ToString.Exclude
    private PostOffice postOffice;

    public Cell() {
    }

    public Cell(String uniqueCode, PostOffice postOffice) {
        this.uniqueCode = uniqueCode;
        this.postOffice = postOffice;
    }

}
