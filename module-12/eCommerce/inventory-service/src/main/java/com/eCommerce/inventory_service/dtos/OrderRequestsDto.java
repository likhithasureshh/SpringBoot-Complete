package com.eCommerce.inventory_service.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestsDto {
    List<OrderRequestItemsDto> items;
}
