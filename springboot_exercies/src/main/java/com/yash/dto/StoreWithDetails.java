package com.yash.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StoreWithDetails {
    private String storeName;
    private boolean isActive;
    private MerchantDTO merchantDTO;
    private List<ItemDto> ItemDtoList;
}
