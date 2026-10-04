package com.eCommerce.orders_service.controllers;

import com.eCommerce.orders_service.Entity.Orders;
import com.eCommerce.orders_service.dtos.OrderRequestDto;
import com.eCommerce.orders_service.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;


    @GetMapping("/helloOrders")
    public String helloOrders()
    {
        return "Hello from Orders-Service";
    }
    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders()
    {
        return ResponseEntity.ok(ordersService.getAllOrders());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id)
    {
        return ResponseEntity.ok(ordersService.getOrderById(id));
    }
}
