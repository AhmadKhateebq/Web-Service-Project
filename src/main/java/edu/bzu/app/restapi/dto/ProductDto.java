package edu.bzu.app.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String slug;
    private String name;
    private String reference;
    private double price;
    private float vat;
    private boolean stackable;
}
