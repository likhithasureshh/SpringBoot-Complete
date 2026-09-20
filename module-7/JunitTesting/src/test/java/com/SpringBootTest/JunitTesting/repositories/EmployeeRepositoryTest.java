package com.SpringBootTest.JunitTesting.repositories;

import com.SpringBootTest.JunitTesting.configs.TestConfigurationConfig;
import com.SpringBootTest.JunitTesting.entities.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(TestConfigurationConfig.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {


    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;
    @BeforeEach
    void setUp()
    {
         employee = Employee.builder()
                .name("likitha")
                .email("liki@gmail.com")
                .salary(100L)
                .build();
    }
    @Test
    void testFindByEmail_whenEmailIsPresent_thenReturnEmployeeList()
    {
        employeeRepository.save(employee);
        List<Employee> employeeList = employeeRepository.findByEmail("liki@gmail.com");
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList).isNotEmpty();
        Assertions.assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());

    }

    @Test
    void testFindByEmail_whenEmailNotFound_thenReturnEmptyEmployeeList()
    {
        String email = "not@gmail.com";
        List<Employee> employeeList = employeeRepository.findByEmail(email);
        Assertions.assertThat(employeeList).isEmpty();
    }
}