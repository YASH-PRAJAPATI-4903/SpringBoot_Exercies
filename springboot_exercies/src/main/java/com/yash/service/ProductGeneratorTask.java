package com.yash.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.yash.Repository.ProductRepository;
import com.yash.entity.Product;

class ProductGeneratorTask implements Runnable {
    private final AtomicLong idCounter;
    private final int maxId;
    private final List<Product> productList;

	private final ProductRepository productRepository;

    public ProductGeneratorTask(AtomicLong idCounter, int maxId, List<Product> productList, ProductRepository productRepository1) {
        this.idCounter = idCounter;
        this.maxId = maxId;
        this.productList = productList;
        this.productRepository = productRepository1;

    }

    @Override
    public void run() {
        while (true) {
            long currentId = idCounter.getAndIncrement();
            if (currentId > maxId) break;

            Product product = new Product("kurkure", "balaji", true, 33L, 50.60, 200.00);
//            synchronized (productList) {
            	if(productRepository.existsById(currentId)) {
        			continue;
        		}

               productRepository.save(product); // thread-safe add
//            }

            System.out.println(Thread.currentThread().getName() + " created " + product);
        }
    }
}