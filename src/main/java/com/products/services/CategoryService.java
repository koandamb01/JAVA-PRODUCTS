package com.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.products.models.Category;
import com.products.models.Product;
import com.products.repositories.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoRepo;
	
	public CategoryService(CategoryRepository categoRepo) { this.categoRepo = categoRepo; }
	
	// create a new category
	public Category createCategory(Category catego) { return this.categoRepo.save(catego); }
	
	// retrieves all the categories
	public List<Category> findAllCategories(){ return this.categoRepo.findAll(); }
	
	// retrieves a category by Id
	public Category findCategoryById(Long id) {
		Optional<Category> res = this.categoRepo.findById(id);
		if(res.isPresent()) {
			return res.get();
		}else {
			return null;
		}
	}
	
	// add product to category
	public void addProduct(Category catego, Product prod) { 
		catego.getProducts().add(prod);
		this.categoRepo.save(catego);
	}
}
