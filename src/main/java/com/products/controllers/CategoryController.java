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
@RequestMapping("/categories")
public class CategoryController {
	private final CategoryService categoService;
	private final ProductService prodService;
	
	public CategoryController(CategoryService categoService, ProductService prodService) { 
		this.categoService = categoService; 
		this.prodService = prodService;
	}
	
	
	@RequestMapping("new")
	public String newForm(@ModelAttribute("category") Category catego) {
		return "category/new.jsp";
	}
	
	@RequestMapping(value="new", method=RequestMethod.POST)
	public String newForm(@Valid @ModelAttribute("category") Category catego, BindingResult formBody) {
		if(formBody.hasErrors()) {
			return "category/new.jsp";
		}
		else {
			Category newCatego = this.categoService.createCategory(catego);
			return "redirect:/categories/"+ newCatego.getId();
		}
	}
	
	
	@RequestMapping("{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		// find the category
		Category category = this.categoService.findCategoryById(id);
		
		// get all the products
		List<Product> products = this.prodService.findAllProducts();
		
		model.addAttribute("products", products);
		model.addAttribute("category", category);
		
		return "category/show.jsp";
	}
	
	
	@RequestMapping(value="{id}/add")
	public String addProduct(@PathVariable("id") Long catego_id, @RequestParam(value="product_id") Long product_id, Model model) {
		// get the category
		Category category = this.categoService.findCategoryById(catego_id);
		
		// get prodcut
		Product product = this.prodService.findProductById(product_id);
		
		// add product to Category
		this.categoService.addProduct(category, product);
		
		// redirect back to dorms
		return "redirect:/categories/" + catego_id;
	}
	
}
