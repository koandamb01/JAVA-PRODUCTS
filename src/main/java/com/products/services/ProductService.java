package com.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.products.models.Product;
import com.products.repositories.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository prodRepo;
	
	public ProductService(ProductRepository prodRepo) { this.prodRepo = prodRepo; }
	
	// create a new product
	public Product createProduct(Product prod) { return this.prodRepo.save(prod); }
	
	// retrieves all the products
	public List<Product> findAllProducts(){ return this.prodRepo.findAll(); }
	
	// retrieves a product by id
	public Product findProductById(Long id) {
		Optional<Product> res = this.prodRepo.findById(id);
		if(res.isPresent()) {
			return res.get();
		}
		else {
			return null;
		}
	}
}
