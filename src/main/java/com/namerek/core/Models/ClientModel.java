package com.namerek.core.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel {

    private long phoneNumber;

    private String name;

    private String lastName;

    private String personalStatus;

    private long EDRPOU;

}
