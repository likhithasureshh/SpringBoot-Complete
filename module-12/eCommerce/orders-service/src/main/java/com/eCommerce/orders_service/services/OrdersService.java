package com.eCommerce.orders_service.services;

import com.eCommerce.orders_service.Entity.OrderItems;
import com.eCommerce.orders_service.Entity.Orders;
import com.eCommerce.orders_service.clients.InventoryFeignClients;
import com.eCommerce.orders_service.dtos.OrderItemRequestDto;
import com.eCommerce.orders_service.dtos.OrderRequestDto;
import com.eCommerce.orders_service.enums.OrderStatus;
import com.eCommerce.orders_service.repositories.OrdersRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;
    private final InventoryFeignClients inventoryFeignClients;
    public List<OrderRequestDto> getAllOrders()
    {
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream()
                .map(orders1 -> modelMapper.map(orders1,OrderRequestDto.class))
                .collect(Collectors.toList());
    }

    public OrderRequestDto getOrderById(Long id) {
        Orders orders = ordersRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Order not found with id :"+id));
        return modelMapper.map(orders, OrderRequestDto.class);
    }

//    @Retry(name = "inventoryRetry",fallbackMethod = "createOrdersFallBack")
//    @RateLimiter(name = "inventoryRateLimiter",fallbackMethod = "createOrdersFallBack")
    @CircuitBreaker(name = "inventoryCircuitBreaker",fallbackMethod = "createOrdersFallBack")
    public OrderRequestDto createOrders(OrderRequestDto orderRequestDto)
    {
        log.info("createOrders execution started");
        Double totalPrice = inventoryFeignClients.reduceStocks(orderRequestDto);
        Orders orders = modelMapper.map(orderRequestDto,Orders.class);
        for(OrderItems orderItems : orders.getOrderItemsList())
        {
            orderItems.setOrders(orders);
        }
        orders.setOrderStatus(OrderStatus.CONFIRMED);
        orders.setTotalPrice(totalPrice);
        Orders savedOrder =ordersRepository.save(orders);
        return modelMapper.map(savedOrder,OrderRequestDto.class);
    }


    public OrderRequestDto createOrdersFallBack(OrderRequestDto orderRequestDto,Throwable throwable)
    {
       log.error("FallBack occurred due to : {}",throwable.getLocalizedMessage());
       return new OrderRequestDto();

    }
}
