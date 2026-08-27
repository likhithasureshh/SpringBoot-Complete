package com.module_3.SpringDataJpa.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table
        (
                name = "product",
                uniqueConstraints =
                        {
                                @UniqueConstraint(name = "sku_unique",columnNames = {"sku"}),
                                @UniqueConstraint(name = "price_title_index",columnNames = {"price","title_x"})
                        },
                indexes = {
                        @Index(name = "sku_index",columnList = "sku"),

                }

        )
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false,length = 20)
    String sku;
    @Column(name = "title_x")
    String title;
    BigDecimal price;
    Integer quantity;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;
}
