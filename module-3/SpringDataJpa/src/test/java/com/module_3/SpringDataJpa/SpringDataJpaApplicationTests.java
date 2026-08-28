package com.module_3.SpringDataJpa;

import com.module_3.SpringDataJpa.entities.Product;
import com.module_3.SpringDataJpa.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringDataJpaApplicationTests {

	@Autowired
	ProductRepository productRepository;
	@Test
	void contextLoads() {
		Product product = Product.builder()
				.sku("guddu-product")
				.title("guddu")
				.price(BigDecimal.valueOf(100))
				.quantity(100)
				.build();
		Product savedProduct = productRepository.save(product);
		System.out.println(product);
	}

	@Test
	void getTitle()
	{

		Optional<Product> product = productRepository.findByTitle("guddu");
		product.ifPresent(System.out::println);
	}

	@Test
	void getList()
	{
		Product product = Product.builder()
				.sku("guddu-product")
				.title("guddu")
				.price(BigDecimal.valueOf(100))
				.quantity(100)
				.build();
		Product savedProduct = productRepository.save(product);
//		List<Product> products = productRepository.findByCreatedAtAfter(LocalDateTime.of(2025,1,1,0,0,0));
//		System.out.println(products);

//		List<Product> products = productRepository.findByQuantityGreaterThanAndPriceLessThan(90,BigDecimal.valueOf(200));
//		System.out.println(products);

//		List<Product> products = productRepository.findByTitleContainingIgnoreCase("guDdu");
		String s = productRepository.findByPriceAndTitle(BigDecimal.valueOf(100),"guddu");
		System.out.println(s);
	}

}
