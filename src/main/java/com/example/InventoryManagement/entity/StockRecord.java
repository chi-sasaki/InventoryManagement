package com.example.InventoryManagement.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StockRecord {
    private String modelNumber;
    private Integer stockQuantity;
    private LocalDateTime lastOrderedAt;
}