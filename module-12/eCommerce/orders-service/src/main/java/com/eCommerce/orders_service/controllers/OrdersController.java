package com.eCommerce.orders_service.controllers;

import com.eCommerce.orders_service.configs.FeaturesEnabledConfig;
import com.eCommerce.orders_service.dtos.OrderRequestDto;
import com.eCommerce.orders_service.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;
    private final FeaturesEnabledConfig featuresEnabledConfig;

    @Value("${my.variable}")
    private String envVariable;




    @GetMapping("/helloOrders")
    public String helloOrders()
    {
        if(featuresEnabledConfig.getIsFeatureEnabled())
        {
            return "feature.enabled thankyou!";
        }
        else {
            return "feature.notenabled thankyou!";
        }
    }

    @PostMapping("/{create-orders}")
    public ResponseEntity<OrderRequestDto> createOrders(@RequestBody OrderRequestDto orderRequestDto)
    {
        return ResponseEntity.ok(ordersService.createOrders(orderRequestDto));
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
