package com.eCommerce.inventory_service.dtos;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private Integer stock;
    private Double price;
}
