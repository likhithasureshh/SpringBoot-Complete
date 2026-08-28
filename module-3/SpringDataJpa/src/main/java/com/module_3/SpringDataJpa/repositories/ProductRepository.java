package com.module_3.SpringDataJpa.repositories;

import com.module_3.SpringDataJpa.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

   List<Product> findByTitleContainingIgnoreCase(String title,Pageable pageable);
    List<Product> findBy(Sort sort);
    @Override
    Page<Product> findAll(Pageable pageable);
    List<Product> findByTitleContainingIgnoreCaseOrderByPrice(String title);

    Optional<Product> findByTitle(String guddu);


    List<Product> findByCreatedAtAfter(LocalDateTime of);

    List<Product> findByQuantityGreaterThanAndPriceLessThan(int quantity, BigDecimal price);

    List<Product> findByTitleLike(String s);

    List<Product> findByTitleContaining(String guddu);

   // List<Product> findByTitleContainingIgnoreCase(String guDdu);

    //Product findByPriceAndTitle(BigDecimal bigDecimal, String guddu);

    @Query("select e.title from Product e where e.price=:price and e.title=:title")
     String findByPriceAndTitle(BigDecimal price,String title);
}
