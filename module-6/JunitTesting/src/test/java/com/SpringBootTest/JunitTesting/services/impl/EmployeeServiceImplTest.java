package com.SpringBootTest.JunitTesting.services.impl;

import com.SpringBootTest.JunitTesting.configs.TestConfigurationConfig;
import com.SpringBootTest.JunitTesting.dto.EmployeeDto;
import com.SpringBootTest.JunitTesting.entities.Employee;
import com.SpringBootTest.JunitTesting.exceptions.ResourceNotFoundException;
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

import static org.assertj.core.api.AssertionsForClassTypes.*;
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
    void testGetEmployeeById_whenEmployeeIsNotPresent_thenThrowException()
    {
        when(employeeRepository.findById(mockedEmployee.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(()-> employeeService.getEmployeeById(mockedEmployee.getId()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: "+mockedEmployee.getId());

        verify(employeeRepository,times(1)).findById(mockedEmployee.getId());
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

    @Test
    void testCreateNewEmployee_whenEmployeeAlreadyPresent_thenThrowException()
    {
        when(employeeRepository.findByEmail(mockedEmployee.getEmail())).
                thenReturn(List.of(mockedEmployee));

        assertThatThrownBy(()->employeeService.createNewEmployee(mockedEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Employee already exists with email: "+mockedEmployee.getEmail());
        verify(employeeRepository,never()).save(mockedEmployee);
        verify(employeeRepository,times(1)).findByEmail(mockedEmployee.getEmail());

    }

    @Test
    void testUpdateEmployee_whenEmployeeExists_thenReturnUpdatedEmployee()
    {
        when(employeeRepository.findById(mockedEmployee.getId())).thenReturn(Optional.of(mockedEmployee));
        when(employeeRepository.save(mockedEmployee)).thenReturn(mockedEmployee);
        mockedEmployeeDto.setName("Likitha");
        EmployeeDto updatedEmployee = modelMapper.map(mockedEmployeeDto,EmployeeDto.class);
        EmployeeDto savedEmployee = employeeService.updateEmployee(mockedEmployee.getId(),updatedEmployee);
        verify(employeeRepository,times(1)).save(mockedEmployee);
        assertThat(savedEmployee.getName())
                .isEqualTo(mockedEmployeeDto.getName());
    }

    @Test
    void testUpdateEmployee_whenEmployeeIsNotPresent_thenThrowException()
    {
        when(employeeRepository.findById(mockedEmployee.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(()-> employeeService.updateEmployee(mockedEmployee.getId(),mockedEmployeeDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: "+mockedEmployee.getId());
        verify(employeeRepository,never()).save(mockedEmployee);
    }

    @Test
    void testUpdateEmployee_whenEmailIsUpdated_thenThrowException()
    {
        when(employeeRepository.findById(mockedEmployee.getId())).thenReturn(Optional.of(mockedEmployee));
        mockedEmployeeDto.setEmail("random");

        EmployeeDto updatedEmployeeDto = modelMapper.map(mockedEmployeeDto, EmployeeDto.class);
        assertThatThrownBy(()-> employeeService.updateEmployee(mockedEmployee.getId(),updatedEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("The email of the employee cannot be updated");
        verify(employeeRepository,never()).save(mockedEmployee);


    }

    @Test
    void testDeleteEmployee_whenEmployeeExists_thenDelete()
    {
        when(employeeRepository.existsById(mockedEmployee.getId())).thenReturn(true);
        assertThatCode(()-> employeeService.deleteEmployee(mockedEmployee.getId()))
                .doesNotThrowAnyException();
    }

    @Test
    void testDeleteEmployee_whenEmployeeDoesNotExists()
    {
        when(employeeRepository.existsById(mockedEmployee.getId())).thenReturn(false);
        assertThatThrownBy(()-> employeeService.deleteEmployee(mockedEmployee.getId()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: "+mockedEmployee.getId());
        verify(employeeRepository,never()).deleteById(mockedEmployee.getId());
    }





}