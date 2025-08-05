//package com.yash.controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
////import com.yash.data.Product;
//import com.yash.service.productService;
//
//@RestController
//@RequestMapping("/product")
//public class productController1 {
//
//	@Autowired
//	private productService prodSrervice;
//
//	@GetMapping("/all")
//	public ResponseEntity<?> getAllProduct(){
//		List<Product> prod = prodSrervice.getAllProduct();
//		if(prod.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data not found!!");
//		}
//		return ResponseEntity.ok(prod);
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getProdById(@PathVariable Long id){
//		Optional<Product> prod = prodSrervice.getById(id);
//		if(prod==null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no data found by given id!!");
//		}
//
//		return ResponseEntity.ok(prod);
//	}
//	@GetMapping("/")
//	public ResponseEntity<?> getProdByName(@RequestParam("name") String name){
//		List<Product> prod = prodSrervice.getByname(name);
//		if(prod.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no data found by given name!!");
//		}
//		return ResponseEntity.ok(prod);
//	}
//
//	@PostMapping("/")
//	public ResponseEntity<?> createproduct(@RequestBody Product p){
//		if(p==null) {
//			return ResponseEntity.ok("no content in given!!!");
//		}
//		String temp= prodSrervice.createProd(p);
//		if(temp==null) {
//			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Dublicate values accure!!");
//		}
//		return ResponseEntity.ok(temp);
//	}
//	@PutMapping("/{id}")
//	public ResponseEntity<?> updateproduct(@PathVariable long id  , @RequestBody Product p){
//		if(p==null) {
//			return ResponseEntity.ok("no content in given!!!");
//		}
//		Product temp= prodSrervice.updateProd(id, p);
//		return ResponseEntity.ok(temp);
//	}
//}
