package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Product;
import com.demo.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	ProductRepository repository;
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

	@GetMapping("/products")
	public List<Product> getProducts() {
		return repository.findAll();
	}

	@Cacheable("product-cache") 
	@Transactional(readOnly = true) 
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable int id) {
		System.out.println("Finding product by ID:"+id);
		return repository.findById(id).get();
	}

	@PostMapping("/products")
	public Product createProduct(@RequestBody Product product) {
		return repository.save(product);
	}

	@CachePut(value="product-cache", key="#id")
	@PutMapping("/products/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable int id) {
		return repository.save(product);
	}

	@CacheEvict(value="product-cache", key="#id")
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable int id) {
		repository.deleteById(id);
	}

}
