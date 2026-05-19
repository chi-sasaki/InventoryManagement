package com.example.InventoryManagement.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StockRecord {
    @NotBlank
    private String modelNumber;
    @NotNull
    @Min(0)
    private Integer stockQuantity;
    private LocalDate lastOrderedAt;
    private Long processId;
}