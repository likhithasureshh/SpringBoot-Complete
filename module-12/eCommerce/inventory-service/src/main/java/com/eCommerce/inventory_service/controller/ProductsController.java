package com.eCommerce.inventory_service.controller;

import com.eCommerce.inventory_service.dtos.ProductDto;
import com.eCommerce.inventory_service.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;

    @GetMapping
    private ResponseEntity<List<ProductDto>> getAllProducts()
    {
        return ResponseEntity.ok(productsService.getAllProducts());
    }

    @GetMapping(path = "/{id}")
    private ResponseEntity<ProductDto> getProductById(@PathVariable Long id)
    {
        return ResponseEntity.ok(productsService.getProductById(id));
    }
}
