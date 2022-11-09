package com.namerek.core.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CellModel {
    private long id;

    private String uniqueCode;

    private PostOfficeModel postOffice;

}
