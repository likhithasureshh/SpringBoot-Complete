package com.eCommerce.inventory_service.services;

import com.eCommerce.inventory_service.dtos.OrderRequestItemsDto;
import com.eCommerce.inventory_service.dtos.OrderRequestsDto;
import com.eCommerce.inventory_service.dtos.ProductDto;
import com.eCommerce.inventory_service.entity.Products;
import com.eCommerce.inventory_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductsService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    public List<ProductDto> getAllProducts()
    {
        log.info("Fetching all products..");
        List<Products> products = productRepository.findAll();
        return products.stream()
                .map(products1 -> modelMapper.map(products1,ProductDto.class))
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id)
    {
        log.info("Fetching products with id:{}",id);
        Products products = productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found with id: "+id));
        return modelMapper.map(products,ProductDto.class);

    }

    @Transactional
    public Double reduceStocks(OrderRequestsDto orderRequestsDto)
    {
        double totalPrice = 0.0;
        for(OrderRequestItemsDto orderRequestItemsDto : orderRequestsDto.getItems())
        {
            Long productId = orderRequestItemsDto.getProductId();
            Integer quantity = orderRequestItemsDto.getQuantity();

            Products products = productRepository.findById(productId)
                    .orElseThrow(()-> new RuntimeException("Products with id doesnt exists"));

            if(quantity > products.getStock())
            {
                throw new RuntimeException("Products with this quantity cannot be shipped");
            }

            products.setStock(products.getStock()-quantity);
            Products savedProducts = productRepository.save(products);
            totalPrice += products.getPrice()*quantity;
        }
        return totalPrice;
    }
}
