package com.yash.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_price")
    private Double itemPrice;
    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "merchant_store_id")
    private int storeId;

    @Column(name = "product_id")
    private Long productId;

}
