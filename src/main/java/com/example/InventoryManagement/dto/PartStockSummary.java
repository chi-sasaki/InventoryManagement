package com.example.InventoryManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartStockSummary {
    private Long partId;
    private String partName;
    private Integer totalIn;
    private Integer totalOut;

    public Integer getDifference() {
        return totalIn - totalOut;
    }
}