package com.yash.data;


public class Product {
	public Long id;
	public String productName;
	public String productBrand;
	public Boolean availabe;
	public Long count;
	public Double packgeGram;
	public Double price;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public Product(Long id, String productName, String productBrand, Boolean availabe, Long count, Double packgeGram,
			Double price) {
		this.id = id;
		this.productName = productName;
		this.productBrand = productBrand;
		this.availabe = availabe;
		this.count = count;
		this.packgeGram = packgeGram;
		this.price = price;
	}
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}