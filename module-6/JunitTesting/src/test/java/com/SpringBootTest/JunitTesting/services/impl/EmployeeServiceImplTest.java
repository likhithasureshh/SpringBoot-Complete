package com.SpringBootTest.JunitTesting.services.impl;

import com.SpringBootTest.JunitTesting.configs.TestConfigurationConfig;
import com.SpringBootTest.JunitTesting.dto.EmployeeDto;
import com.SpringBootTest.JunitTesting.entities.Employee;
import com.SpringBootTest.JunitTesting.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Import(TestConfigurationConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee mockedEmployee;
    private EmployeeDto mockedEmployeeDto;
    @BeforeEach
    void setUp()
    {
         mockedEmployee = Employee.builder()
                 .id(1L)
                .email("liki@gmail.com")
                .name("Likitha")
                .salary(2000L)
                .build();

        mockedEmployeeDto = modelMapper.map(mockedEmployee, EmployeeDto.class);
    }

    @Test
    void testGetEmployeeById_whenEmployeeIsValid_thenReturnListOfEmployees()
    {
        Long id = mockedEmployee.getId();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockedEmployee));

        EmployeeDto employeeDto = employeeService.getEmployeeById(id);

        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getEmail()).isEqualTo(mockedEmployee.getEmail());
        verify(employeeRepository,atLeastOnce()).findById(id);
    }


    @Test
    void testCreateNewEmployee_whenEmployeeIsValid_thenReturnEmployee()
    {
        when(employeeRepository.findByEmail(anyString())).thenReturn(List.of());
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockedEmployee);

        EmployeeDto savedEmployee = employeeService.createNewEmployee(mockedEmployeeDto);

        ArgumentCaptor<Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getEmail()).isEqualTo(mockedEmployeeDto.getEmail());
        verify(employeeRepository).save(argumentCaptor.capture());
        Employee captured = argumentCaptor.getValue();
        assertThat(captured.getEmail()).isEqualTo(mockedEmployee.getEmail());

    }





}