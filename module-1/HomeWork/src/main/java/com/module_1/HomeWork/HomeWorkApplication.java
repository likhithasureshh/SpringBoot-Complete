package com.module_1.HomeWork;

import com.module_1.HomeWork.impl.CakeBaker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class HomeWorkApplication implements CommandLineRunner {
	private final CakeBaker cakeBaker;

	public static void main(String[] args) {
		SpringApplication.run(HomeWorkApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		cakeBaker.bakeCake();
	}
}
