package com.namerek.core.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOfficeDTO {

    private long officeNumber;

    private AddressDTO address;

    private String postType;
}
