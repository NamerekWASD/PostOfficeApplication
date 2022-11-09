package com.namerek.core.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientDTO {

    private long phoneNumber;

    private String name;

    private String lastName;

    private String personalStatus;

    private long EDRPOU;

}
