package com.yash.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.Repository.ProductRepository;
import com.yash.data.Product;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	private Product product;

	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}

	public Product createProd(Product p) {
		return productRepository.save(p);
	}

	public Product updateProd(long id, Product p) {

		return productRepository.findById(id).map(ex->{
			ex.setId(id);
			ex.setName(p.getName());
			ex.setProductBrand(p.getProductBrand());
			ex.setAvailabe(p.getAvailabe());
			ex.setCount(p.getCount());
			ex.setPackgeGram(p.getPackgeGram());
			ex.setPrice(p.getPrice());
			return productRepository.save(ex);
		}).orElseThrow(()->new  RuntimeException("Department not found!!!"));
	}

	public Optional<Product> getById(Long id) {
		return productRepository.findById(id);
	}

	public List<Product> getByName(String name) {

		return productRepository.findByNameContaining(name);
	}

	public void deleteAll() {
		productRepository.deleteAll();
	}


	public void AutoGenrateProduct(int totalProducts, Long counterStart) {


        AtomicLong idCounter = new AtomicLong(counterStart);


        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, 20,
                60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>()
        );
        int initialThreads = 10;
        for (int i = 0; i < initialThreads; i++) {
            executor.execute(new ProductGeneratorTask(idCounter, totalProducts, productRepository.findAll(),  productRepository));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTotal Products Generated: " + productRepository.count());
    }

}

