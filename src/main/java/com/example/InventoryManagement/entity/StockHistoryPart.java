package com.example.InventoryManagement.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StockHistoryPart {
    private Long id;
    private Long partId;
    private Integer quantity;
    private LocalDate actionAt;
}
