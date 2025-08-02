package com.yash.data;


public class Product {
	public long id;
	public String productName;
	public String productBrand;
	public boolean availabe;
	public long count;
	public double packgeGram;
	public double price;
	
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
	public double getPackgeGram() {
		return packgeGram;
	}
	public void setPackgeGram(double packgeGram) {
		this.packgeGram = packgeGram;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Product(long id, String productName, String productBrand, boolean availabe, long count, double packgeGram,
			double price) {
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