package com.yash.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yash.data.Product;



@Service
public class productService {

	List<Product> list = new ArrayList<>();
	public String createProd(Product p) {
		list.add(p);
		return "Product added!!!";
	}
	
	public List<Product> getById(long id) {
		List<Product> p=list.stream().filter(e-> e.getId()==id).collect(Collectors.toList());
		return p;
	}
	public List<Product> getByname(String name) {
		List<Product> p=list.stream().filter(e-> e.getProductName().equals(name)).collect(Collectors.toList());
		return p;
	}
}
