package com.namerek.core.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostOfficeModel {

    private long officeNumber;

    private AddressModel address;

    private String postType;

}
