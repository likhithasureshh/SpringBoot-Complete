package com.springboot.prod_ready_features.clients;

import com.springboot.prod_ready_features.dtos.EmployeeDto;

import java.util.List;

public interface RestClientIn {
    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long id);

    EmployeeDto createNewEmployee(EmployeeDto employeeDto);
}
