package com.products.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.products.models.Category;
import com.products.models.Product;
import com.products.services.CategoryService;
import com.products.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	private final ProductService prodService;
	private final CategoryService categoService;
	
	public ProductController(ProductService prodService, CategoryService categoService) {
		this.prodService = prodService; 
		this.categoService = categoService;
	}
	
	@RequestMapping("new")
	public String newForm(@ModelAttribute("product") Product prod) {
		return "product/new.jsp";
	}
	
	@RequestMapping(value="new", method=RequestMethod.POST)
	public String newForm(@Valid @ModelAttribute("product") Product prod, BindingResult formBody) {
		if(formBody.hasErrors()) {
			return "product/new.jsp";
		}
		else {
			this.prodService.createProduct(prod);
		}
		return "redirect:/categories/new";
	}
	
	
	@RequestMapping("{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		// find the category
		Product product = this.prodService.findProductById(id);
		
		// get all the products
		List<Category> categories = this.categoService.findAllCategories();
		
		model.addAttribute("product", product);
		model.addAttribute("categories", categories);
		
		return "product/show.jsp";
	}
	
	
	
	@RequestMapping(value="{id}/add")
	public String addProduct(@PathVariable("id") Long product_id, @RequestParam(value="category_id") Long catego_id, Model model) {
		// get product
		Product product = this.prodService.findProductById(product_id);
		
		// get the category
		Category category = this.categoService.findCategoryById(catego_id);
		
		// add product to Category
		this.categoService.addProduct(category, product);
		
		// redirect back to dorms
		return "redirect:/products/" + product_id;
	}
}
