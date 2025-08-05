package com.yash.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.data.Product;
import com.yash.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService prodSrervice;

	@GetMapping("/all")
	public ResponseEntity<?> getAllProduct(){
		List<Product> prod = prodSrervice.getAllProduct();
		if(prod.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(prod);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProdById(@PathVariable(name="id") Long id){
		Optional<Product> prod = prodSrervice.getById(id);
		if(!prod.isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		return ResponseEntity.ok(prod);
	}
	@GetMapping("/")
	public ResponseEntity<?> getProdByName(@RequestParam("name") String name){
		List<Product> prod = prodSrervice.getByname(name);
		if(prod.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(prod);
	}

	@PostMapping("/")
	public ResponseEntity<?> createproduct(@RequestBody Product p){
		if(p==null) {
			return ResponseEntity.ok("no content in given!!!");
		}
		Product temp= prodSrervice.createProd(p);
		if(temp==null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Dublicate values accure!!");
		}
		return ResponseEntity.ok(temp);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateproduct(@PathVariable(name="id") Long id  , @RequestBody(required = false) Product p){
		if(p==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body missing!!!");
		}
		Product temp= prodSrervice.updateProd(id, p);
		if(temp==null) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
		return ResponseEntity.ok(temp);
	}
	@DeleteMapping("/")
	public String deleteAll() {
		prodSrervice.deleteAll();
		return "All Products deleted!!!";
	}
	@PostMapping("/auto")
	public String generateAuto(@RequestParam("tp") int tp,@RequestParam("c") Long c ) {
		prodSrervice.AutoGenrateProduct(tp,c);
		return "All Products generated!!!";
	}
}
