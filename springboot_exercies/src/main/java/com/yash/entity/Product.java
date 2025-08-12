package com.yash.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_name")
	private String name;
	@Column(name = "product_brand")
	private String productBrand;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(name = "product_id")
	private List<Item> itemList;



	public Product( String name, String productBrand, Boolean availabe, Long count, Double packgeGram, Double price){
		this.name = name;
		this.productBrand = productBrand;
	}



	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", productBrand='" + productBrand + '\'' +
				'}';
	}
}