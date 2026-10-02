package com.learnDocker.docker_learn.controller;

import com.learnDocker.docker_learn.entity.Product;
import com.learnDocker.docker_learn.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.GroovyWebApplicationContext;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping(path = "/hello")
    public String sayHello()
    {
        return "Hello from Spring Boot!";
    }
    @PostMapping
    private String createProduct(@RequestBody Product product)
    {
        return productService.createProduct(product);
    }

    @GetMapping
    private List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }
}
