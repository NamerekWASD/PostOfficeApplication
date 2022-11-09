package com.namerek.core.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {

    private long phone_number;

    private String name;

    private String lastName;

    private AddressModel address;

    private Date dateOfJoin;

    private String qualification;

    private PostOfficeModel postOffice;

}
