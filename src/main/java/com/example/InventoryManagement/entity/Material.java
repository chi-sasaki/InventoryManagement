package com.example.InventoryManagement.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Material extends StockRecord {
    private Long id;
    private String materialName;
    private Long processId;
}
