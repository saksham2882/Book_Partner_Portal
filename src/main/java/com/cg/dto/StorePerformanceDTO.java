package com.cg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorePerformanceDTO {
    private String storeId;
    private String storeName;
    private String storeAddress;
    private String city;
    private String state;
    private String zip;
    private Long totalOrders;
    private Long totalSalesQuantity;
}

