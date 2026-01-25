package com.example.InventoryManagement.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Part extends StockRecord {
    private Long id;
    @NotBlank
    private String partName;
    @NotNull
    private Long processId;
}
