package com.yash.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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