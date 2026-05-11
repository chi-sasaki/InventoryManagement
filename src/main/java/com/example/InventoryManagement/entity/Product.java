package com.example.InventoryManagement.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends StockRecord {
    private Long id;
    @NotBlank
    private String productName;
    private Long companyId;
    private String companyName;
}
