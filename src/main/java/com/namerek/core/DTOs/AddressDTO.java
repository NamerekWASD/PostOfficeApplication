package com.namerek.core.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private long id;
    private String country;
    private String city;
    private String street;
    private String building;
    private String postalCode;
    private boolean isPostOffice;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        AddressDTO address;
        if (obj instanceof AddressDTO) {
            address = (AddressDTO) obj;
        } else {
            return false;
        }
        return Objects.equals(country, address.getCountry()) &&
                Objects.equals(city, address.getCity()) &&
                Objects.equals(street, address.getStreet()) &&
                Objects.equals(building, address.getBuilding()) &&
                Objects.equals(postalCode, address.getPostalCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, building, postalCode, isPostOffice);
    }

    @Override
    public String toString() {
        String str = country +
                ", " + city +
                ", " + street +
                ", " + building +
                ", " + postalCode ;
        str += isPostOffice ? "(Post office)" : "(Not post office)";
        return str;
    }
}
