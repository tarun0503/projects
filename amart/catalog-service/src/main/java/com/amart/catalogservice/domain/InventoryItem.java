package com.amart.catalogservice.domain;

import lombok.Data;

@Data
public class InventoryItem {
    private String productCode;
    private Integer availableQuantity;
}
