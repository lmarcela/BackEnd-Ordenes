package com.marcela.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcela.model.Product;
import com.marcela.service.ProductService;

/**
 * Created by marcela
 */

@CrossOrigin
@RestController
public class ProductsController {

	@Autowired
	ProductService productService;

	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getProducts();
	}

	@GetMapping("/product/{productId}")
	public Product getProduct(@PathVariable int productId) {
		return productService.getProduct(productId);
	}

	@PostMapping("/product")
	public Product createProduct(@RequestBody Product product) {
		try {
			return productService.createProduct(product);
		} catch (Exception e) {
			return null;
		}
	}

	@PutMapping("/product")
	public Product updateProduct(@RequestBody Product product) {
		if (productService.existProduct(product)) {
			return productService.updateProduct(product);
		} else {
			return null;
		}
	}

	@DeleteMapping("/product/{productId}")
	public boolean deleteUser(@PathVariable int productId) {
		try {
			return productService.deleteProduct(productId);
		} catch (Exception e) {
			return false;
		}
	}

}