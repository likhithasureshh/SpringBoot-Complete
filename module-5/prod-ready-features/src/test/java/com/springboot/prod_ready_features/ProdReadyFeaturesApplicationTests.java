package com.springboot.prod_ready_features;

import com.springboot.prod_ready_features.entities.User;
import com.springboot.prod_ready_features.service.impl.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdReadyFeaturesApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void generateToken()
	{
		User user = new User(4L,"liki@gmail.com","123");

		String token = jwtService.generateToken(user);
		System.out.println(token);

		System.out.println(jwtService.getUserId(token));
	}
}
