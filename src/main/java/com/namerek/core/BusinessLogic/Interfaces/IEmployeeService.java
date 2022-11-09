package com.namerek.core.BusinessLogic.Interfaces;

import com.namerek.core.DTOs.EmployeeDTO;

public interface IEmployeeService {
    EmployeeDTO getById(long id);
    EmployeeDTO Login(long id, String password);
}
