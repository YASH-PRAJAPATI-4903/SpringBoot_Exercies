package com.yash.service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.yash.data.Product;

class ProductGeneratorTask implements Runnable {
    private final AtomicLong idCounter;
    private final int maxId;
    private final Map<Long, Product> productList;

    public ProductGeneratorTask(AtomicLong idCounter, int maxId, Map<Long, Product> productList) {
        this.idCounter = idCounter;
        this.maxId = maxId;
        this.productList = productList;
    }

    @Override
    public void run() {
        while (true) {
            long currentId = idCounter.getAndIncrement();
            if (currentId > maxId) break;

            Product product = new Product(currentId, "kurkure", "balaji", true, 33, 50.60, 200);
//            synchronized (productList) {
            	if(productList.containsKey(currentId)) {
//        			return new RuntimeException().getMessage();
        			continue;
        		}
            
                productList.put(currentId, product); // thread-safe add
//            }

            System.out.println(Thread.currentThread().getName() + " created " + product);
        }
    }
}