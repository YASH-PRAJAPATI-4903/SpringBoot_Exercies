package com.yash.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemWithAllDetailsDTO {
    private Long id;
    private Double itemPrice;
    private Long quantity;
    private MerchantStoreDTO merchantStoreDTO;
    private ProductDTO productDTO;
}
