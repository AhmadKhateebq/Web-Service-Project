package edu.bzu.app.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDto {
    private Long id;
    private ProductDto product;
    private OrderDto order;
    private int quantity;
    private double price;
    private double vat;
}

