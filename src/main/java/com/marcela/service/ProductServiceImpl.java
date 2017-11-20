package com.marcela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcela.model.Product;
import com.marcela.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Iterable<Product> getListarProductos() {		
		return productRepository.findAll();
	}

	@Override
	public Product getBuscarProducto(int producto) {
		return productRepository.findOne(producto);
	}

}