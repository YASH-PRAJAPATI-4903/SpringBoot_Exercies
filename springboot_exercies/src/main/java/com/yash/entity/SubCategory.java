package com.yash.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sub_category")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sub_category_name")
    private String name;

    @Column(name = "category_id")
    private int categoryId;

    @OneToMany(mappedBy = "subCategoryID", cascade = CascadeType.ALL, orphanRemoval = false)
//    @JsonIgnore
    private List<Product> productList;

}
