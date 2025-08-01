package com.yash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.data.Product;
import com.yash.service.productService;

@RestController
public class productController {
	
	@Autowired
	private productService prodSrervice;
	
	@GetMapping("/{id}")
	public List<Product> getProdById(@PathVariable int id){
		List<Product> prod = prodSrervice.getById(id);
		return prod;
	}
	@GetMapping("/")
	public List<Product> getProdById(@RequestParam("name") String name){
		List<Product> prod = prodSrervice.getByname(name);
		return prod;
	}
	
	@PostMapping("/")
	public ResponseEntity<?> createproduct(@RequestBody Product p){
		if(p==null) {
			return ResponseEntity.ok("no content in given!!!");
		}
		String temp= prodSrervice.createProd(p);
		return ResponseEntity.ok(temp);
	}
	
}
