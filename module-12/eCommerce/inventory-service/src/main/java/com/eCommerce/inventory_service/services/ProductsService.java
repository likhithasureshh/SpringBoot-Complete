package com.eCommerce.inventory_service.services;

import com.eCommerce.inventory_service.dtos.ProductDto;
import com.eCommerce.inventory_service.entity.Products;
import com.eCommerce.inventory_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductsService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    public List<ProductDto> getAllProducts()
    {
        log.info("Fetching all products..");
        List<Products> products = productRepository.findAll();
        return products.stream()
                .map(products1 -> modelMapper.map(products1,ProductDto.class))
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id)
    {
        log.info("Fetching products with id:{}",id);
        Products products = productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found with id: "+id));
        return modelMapper.map(products,ProductDto.class);

    }
}
