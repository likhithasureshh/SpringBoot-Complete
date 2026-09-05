package com.springboot.prod_ready_features;

import com.springboot.prod_ready_features.clients.RestClientIn;
import com.springboot.prod_ready_features.dtos.EmployeeDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdReadyFeaturesApplicationTests {
	@Autowired
	private RestClientIn restClientIn;

	@Test
	@Order(1)
	void createNewEmployee()
	{
		EmployeeDto employeeDto = EmployeeDto
				.builder()
				.age(2)
				.name("Likitha")
				.email("Likitha@gmail.com")
				.salary(1000.00)
				.role("USER")
				.isActive(true)
				.dateOfJoining(LocalDate.now())
				.build();
		System.out.println(restClientIn.createNewEmployee(employeeDto));
	}

	@Test
	@Order(2)
	void getEmployeeById()
	{
		System.out.println(restClientIn.getEmployeeById(1L));
	}

	@Test
	@Order(3)
	void getAllEmployees()
	{
		System.out.println(restClientIn.getAllEmployees());
	}

}
