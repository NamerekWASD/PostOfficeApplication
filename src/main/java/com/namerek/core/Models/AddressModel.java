package com.namerek.core.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
    private long id;
    private String country;
    private String city;
    private String street;
    private String building;
    private String postalCode;
    private boolean isPostOffice;

    public AddressModel(String country, String city, String street,
                        String building, String postalCode, boolean isPostOffice) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.postalCode = postalCode;
        this.isPostOffice = isPostOffice;
    }
}
