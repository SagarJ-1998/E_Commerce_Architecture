package com.ecom.techie.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "product_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private String description;
    private BigDecimal price;
    private int quantity;
    private String brand;
}
