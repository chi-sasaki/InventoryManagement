package com.example.InventoryManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartStockSummary {
    private Long partId;
    private String partName;
    private String modelNumber;
    private Integer totalIn;
    private Integer totalOut;

    public Integer getDifference() {
        return (totalIn == null ? 0 : totalIn) - (totalOut == null ? 0 : totalOut);
    }
}