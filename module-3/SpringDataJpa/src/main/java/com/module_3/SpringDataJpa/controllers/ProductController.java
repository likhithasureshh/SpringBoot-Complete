package com.module_3.SpringDataJpa.controllers;

import com.module_3.SpringDataJpa.entities.Product;
import com.module_3.SpringDataJpa.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final int PAGE_SIZE = 5;
    private final ProductRepository productRepository;
    @GetMapping
    public List<Product> productList(@RequestParam(defaultValue = "id") String sortBy
    , @RequestParam(defaultValue = "0") Integer pageNum)
    {
       // return productRepository.findByTitleContainingIgnoreCaseOrderByPrice("b");
      // return productRepository.findBy(Sort.by(Sort.Direction.ASC, sortBy,"price"));
        //return productRepository.findBy(Sort.by(Sort.Order.asc(sortBy),Sort.Order.desc("price")));
        //return productRepository.findAll(PageRequest.of(pageNum,PAGE_SIZE,Sort.by(sortBy))).getContent();
        return productRepository.findByTitleContainingIgnoreCase("a",
                PageRequest.of(pageNum,PAGE_SIZE,Sort.by(sortBy)));
    }
}
