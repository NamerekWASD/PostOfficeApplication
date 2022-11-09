package com.namerek.core.BusinessLogic.Services;

import com.namerek.core.BusinessLogic.Interfaces.IEmployeeService;
import com.namerek.core.DTOs.EmployeeDTO;
import com.namerek.core.Entities.Employee;
import com.namerek.core.Mappers.ModelMapperConverter;
import com.namerek.core.UnitOfWorks.GenericRepository;
import com.namerek.core.UnitOfWorks.IGenericRepository;

import java.util.Objects;

public class EmployeeService implements IEmployeeService {

    ModelMapperConverter<Employee, EmployeeDTO> mapper = new ModelMapperConverter<>(Employee.class, EmployeeDTO.class);

    IGenericRepository<Employee, Long> employeeRepository;
    public  EmployeeService(){
        employeeRepository = new GenericRepository<>(Employee.class);
    }

    @Override
    public EmployeeDTO getById(long id) {
        return mapper.toB(employeeRepository.find(id));
    }

    @Override
    public EmployeeDTO Login(long id, String password) {
        EmployeeDTO employee = getById(id);

        if(Objects.equals(employee.getPassword(),password)){
            return employee;
        }
        return null;
    }
}
