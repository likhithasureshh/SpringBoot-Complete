package com.eCommerce.orders_service.dtos;

import com.eCommerce.orders_service.Entity.OrderItems;
import lombok.Data;

import java.util.List;
@Data
public class OrderRequestDto {
    private Long id;
    private Double totalPrice;
}
