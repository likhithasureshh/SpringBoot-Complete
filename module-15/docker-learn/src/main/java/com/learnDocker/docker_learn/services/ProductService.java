package com.learnDocker.docker_learn.services;

import com.learnDocker.docker_learn.entity.Product;
import com.learnDocker.docker_learn.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public String createProduct(Product product)
    {
        productRepository.save(product);
        return "Product is created";
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
