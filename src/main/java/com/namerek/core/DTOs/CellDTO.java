package com.namerek.core.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CellDTO {
    private long id;

    private String uniqueCode;

    private PostOfficeDTO postOffice;

    @Override
    public String toString() {
        return uniqueCode;
    }
}
