package com.marcela.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcela.model.Product;
import com.marcela.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(int productId) {
		return productRepository.findOne(productId);
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public boolean deleteProduct(int productId) {
		productRepository.delete(productId);
		return true;
	}

	@Override
	public boolean existProduct(Product product) {
		return productRepository.exists(product.getProductId());
	}

}