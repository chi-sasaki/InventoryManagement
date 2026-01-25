package com.example.InventoryManagement.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManufacturingProcess {
    private Long id;
    private String processName;
    private Integer sortOrder;
}
