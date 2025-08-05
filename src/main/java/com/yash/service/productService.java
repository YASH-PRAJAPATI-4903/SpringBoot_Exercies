package com.yash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class productService {
//	List<Product> list = new ArrayList<>();
//	private static final Map<Long, Product> map= new HashMap<>();
	@Autowired
	private ProductRepository productRepository;
	private Product product;

	public List<Product> getAllProduct(){
//		return map.values().stream().toList();
		return productRepository.findAll();
	}

	public Product createProd(Product p) {
//		if(map.containsKey(p.getId())) {
////			return new RuntimeException().getMessage();
//			return null;
//		}

//		list.add(p);
//		map.put(p.getId(), p);
//		productRepository.save(p);
		return productRepository.save(p);
	}

	public Product updateProd(long id, Product p) {

//		if(!map.containsKey(id)) {
//			return null;
//		}
//		if(!productRepository.existsById(id)) {
//			return null;
//		}
//		map.replace(id, p);
//		map.get(id).setId(id);
//		map.get(id).setProductName(p.getProductName());
//		map.get(id).setProductBrand(p.getProductBrand());
//		map.get(id).setAvailabe(p.isAvailabe());
//		map.get(id).setCount(p.getCount());
//		map.get(id).setPackgeGram(p.getPackgeGram());
//		map.get(id).setPrice(p.getPrice());

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
//		Product p=map.get(id);

		return productRepository.findById(id);
	}

	public List<Product> getByname(String name) {
//		List<Product> p= map.values().stream().filter(v->v.getProductName().contains(name)).toList();

		return productRepository.findByNameContaining(name);
	}

	public void deleteAll() {
//		map.clear();
		productRepository.deleteAll();
//		map=new HashMap<>();
	}

//	public Map< Long, Product> getMap(){
////		return map;
//	}

	public void AutoGenrateProduct(int totalProducts, Long counterStart) {


        // Shared resources
        AtomicLong idCounter = new AtomicLong(counterStart);

//        Map<Long,Product> productList = new HashMap<>();

        // ThreadPoolExecutor with min 5 and max 20 threads
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, 20,
                60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>()
        );

        // Submit initial 5-20 worker tasks (for example 10)
        int initialThreads = 10;
        for (int i = 0; i < initialThreads; i++) {
            executor.execute(new ProductGeneratorTask(idCounter, totalProducts, productRepository.findAll(),  productRepository));
        }

        // Shutdown after completion
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTotal Products Generated: " + productRepository.count());
    }

}

