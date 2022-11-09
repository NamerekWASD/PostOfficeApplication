package com.namerek.core.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeeDTO {

    private long phone_number;

    private String name;

    private String lastName;

    private AddressDTO address;

    private Date dateOfJoin;

    private String qualification;

    private PostOfficeDTO postOffice;

    private String password;

}
