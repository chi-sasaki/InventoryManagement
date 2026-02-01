package com.example.InventoryManagement.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StockRecord {
    @NotBlank
    private String modelNumber;
    private Integer stockQuantity;
    private LocalDate lastOrderedAt;
    private Long processId;
}