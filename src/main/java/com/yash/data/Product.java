package com.yash.data;


public class Product {
	public long id;
	public String productName;
	public String productBrand;
	public boolean availabe;
	public long count;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public boolean isAvailabe() {
		return availabe;
	}
	public void setAvailabe(boolean availabe) {
		this.availabe = availabe;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}

	public Product(long id, String productName, String productBrand, boolean availabe, long count) {
		this.id = id;
		this.productName = productName;
		this.productBrand = productBrand;
		this.availabe = availabe;
		this.count = count;
	}
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
}