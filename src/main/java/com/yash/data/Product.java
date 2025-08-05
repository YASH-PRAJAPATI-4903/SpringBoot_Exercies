package com.yash.data;


import jakarta.persistence.*;

@Entity
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name = "product_name")
	public String name;
	@Column(name = "product_brand")
	public String productBrand;
	public Boolean availabe;
	public Long count;
	@Column(name = "packge_gram")
	public Double packgeGram;
	public Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public Boolean getAvailabe() {
		return availabe;
	}

	public void setAvailabe(Boolean availabe) {
		this.availabe = availabe;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Double getPackgeGram() {
		return packgeGram;
	}

	public void setPackgeGram(Double packgeGram) {
		this.packgeGram = packgeGram;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Product() {
	}

	public Product(Long id, String name, String productBrand, Boolean availabe, Long count, Double packgeGram, Double price) {
		this.id = id;
		this.name = name;
		this.productBrand = productBrand;
		this.availabe = availabe;
		this.count = count;
		this.packgeGram = packgeGram;
		this.price = price;
	}

	public Product( String name, String productBrand, Boolean availabe, Long count, Double packgeGram, Double price){
		this.name = name;
		this.productBrand = productBrand;
		this.availabe = availabe;
		this.count = count;
		this.packgeGram = packgeGram;
		this.price = price;
	}



	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", productBrand='" + productBrand + '\'' +
				", availabe=" + availabe +
				", count=" + count +
				", packgeGram=" + packgeGram +
				", price=" + price +
				'}';
	}
}