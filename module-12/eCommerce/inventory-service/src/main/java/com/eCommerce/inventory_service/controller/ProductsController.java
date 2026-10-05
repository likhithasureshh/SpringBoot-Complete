package com.eCommerce.inventory_service.controller;

import com.eCommerce.inventory_service.clients.OrdersFeignClients;
import com.eCommerce.inventory_service.dtos.OrderRequestsDto;
import com.eCommerce.inventory_service.dtos.ProductDto;
import com.eCommerce.inventory_service.services.ProductsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;
    private final OrdersFeignClients ordersFeignClients;


    @GetMapping("/fetchOrders")
    public String fetchFromOrders(HttpServletRequest httpServletRequest)
    {
//        ServiceInstance orderService = discoveryClient.getInstances("orders-service").getFirst();
//        return restClient.get()
//                .uri(orderService.getUri()+"/orders/core/helloOrders")
//                .retrieve()
//                .body(String.class);
        return ordersFeignClients.fetchOrders();
    }

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

    @PutMapping("/reduce-stocks")
    public ResponseEntity<Double> reduceStocks(@RequestBody OrderRequestsDto orderRequestsDto)
    {
        return ResponseEntity.ok(productsService.reduceStocks(orderRequestsDto));
    }
}
