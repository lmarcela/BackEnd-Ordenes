package com.marcela.service;

import java.util.List;

import com.marcela.model.Product;

public interface ProductService {

	public List<Product> getProducts();

	public Product getProduct(int productId);

	public Product createProduct(Product product);

	public Product updateProduct(Product product);

	public boolean deleteProduct(int productId);

	public boolean existProduct(Product product);
}