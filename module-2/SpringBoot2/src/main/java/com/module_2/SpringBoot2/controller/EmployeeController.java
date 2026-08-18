package com.module_2.SpringBoot2.controller;

import com.module_2.SpringBoot2.dtos.EmployeeDto;
import com.module_2.SpringBoot2.entities.EmployeeEntity;
import com.module_2.SpringBoot2.repositories.EmployeeRepository;
import com.module_2.SpringBoot2.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    @GetMapping(path = "/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable(name = "employeeId") Long id)
    {
       return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees()
    {
       return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDto createNewEmployee(@RequestBody EmployeeDto employeeDto)
    {
        return employeeService.createNewEmployee(employeeDto);
    }


}
