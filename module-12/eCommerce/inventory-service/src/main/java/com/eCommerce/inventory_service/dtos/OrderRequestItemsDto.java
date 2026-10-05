package com.eCommerce.inventory_service.dtos;

import lombok.Data;

@Data
public class OrderRequestItemsDto {
    Long productId;
    Integer quantity;

}
