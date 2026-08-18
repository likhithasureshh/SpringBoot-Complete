package com.module_2.SpringBoot2.services;

import com.module_2.SpringBoot2.dtos.EmployeeDto;
import com.module_2.SpringBoot2.entities.EmployeeEntity;
import com.module_2.SpringBoot2.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    public EmployeeDto getEmployeeById(Long id)
    {
        return modelMapper.map(employeeRepository.findById(id).orElse(null),EmployeeDto.class);
    }


    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        return modelMapper.map(employeeRepository.save
                (modelMapper.map(employeeDto,EmployeeEntity.class)),EmployeeDto.class);
    }
}
