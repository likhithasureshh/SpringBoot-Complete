package com.eCommerce.orders_service.dtos;

import com.eCommerce.orders_service.Entity.Orders;
import lombok.Data;

@Data
public class OrderItemRequestDto {
    private Long id;

    private Integer quantity;

    private Long productId;

}
