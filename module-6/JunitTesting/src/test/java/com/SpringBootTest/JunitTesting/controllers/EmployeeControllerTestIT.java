package com.SpringBootTest.JunitTesting.controllers;

import com.SpringBootTest.JunitTesting.dto.EmployeeDto;
import com.SpringBootTest.JunitTesting.entities.Employee;
import com.SpringBootTest.JunitTesting.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.utility.TestcontainersConfiguration;

import static org.junit.jupiter.api.Assertions.*;
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "100000")
class EmployeeControllerTestIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Employee employee;
    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp()
    {
        employee = Employee
                .builder()
                .email("liki@gmail.com")
                .name("likitha")
                .salary(1000L)
                .build();
        employeeDto = modelMapper.map(employee, EmployeeDto.class);
        employeeRepository.deleteAll();
    }

    @Test
    void testGetEmployeeById_whenEmployeeExists()
    {
        Employee savedEmployee = employeeRepository.save(employee);
        employeeDto.setId(savedEmployee.getId());
        webTestClient.get()
                .uri("/employees/{id}",savedEmployee.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeDto.class)
                .isEqualTo(employeeDto);
    }

    @Test
    void testGetEmployeeById_whenEmployeeDoesNotExists()
    {
        webTestClient.get()
                .uri("/employees/100")
                .exchange()
                .expectStatus().isNotFound();

    }

    @Test
    void testCreateNewEmployee_success()
    {

        webTestClient.post()
                .uri("/employees")
                .bodyValue(employeeDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.email").isEqualTo(employeeDto.getEmail());

    }

    @Test
    void testCreateNewEmployeeFailure()
    {
        Employee savedEmployee = employeeRepository.save(employee);
        webTestClient.post()
                .uri("/employees")
                .bodyValue(employeeDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testUpdateEmployee_whenEmployeeNotFound()
    {
        webTestClient.put()
                .uri("/employees/100")
                .bodyValue(employeeDto)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateEmployee_whenEmailUpdated()
    {
        Employee savedEmployee = employeeRepository.save(employee);
        employeeDto.setEmail("randome@gamil.com");
        employeeDto.setName("random");
        webTestClient.put()
                .uri("/employees/{id}",employee.getId())
                .bodyValue(employeeDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testUpdateEmployee_success()
    {
        Employee savedEmployee = employeeRepository.save(employee);
        employeeDto.setId(savedEmployee.getId());
        employeeDto.setName("random");
        webTestClient.put()
                .uri("/employees/{id}",savedEmployee.getId())
                .bodyValue(employeeDto)
                .exchange()
                .expectBody(EmployeeDto.class)
                .isEqualTo(employeeDto);
    }

    @Test
    void deleteEmployee_success()
    {
        Employee savedEmployee = employeeRepository.save(employee);
        webTestClient.delete()
                .uri("/employees/{id}",savedEmployee.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody(Void.class);
    }
    @Test
    void deleteEmployee_failure()
    {
        webTestClient.delete()
                .uri("/employees/100")
                .exchange()
                .expectStatus().isNotFound();
    }
}