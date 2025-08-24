package com.yash.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class storeWithMerchant {
    private String storeName;
    private boolean isActive;
    private MerchantDTO merchantDTO;
}
