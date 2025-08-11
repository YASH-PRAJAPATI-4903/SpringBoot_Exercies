package com.yash.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "store")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "merchant_name")
    private String name;
    @Column(name = "merchant_mobile")
    private String mobile;
    @Column(name = "merchant_email")
    private String email;

    @OneToMany(mappedBy = "merchantId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MerchantStore> merchantStores;
}
