package com.eCommerce.inventory_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "orders-service",path = "/orders")
public interface OrdersFeignClients {

    @GetMapping("/core/helloOrders")
    String fetchOrders();
}
