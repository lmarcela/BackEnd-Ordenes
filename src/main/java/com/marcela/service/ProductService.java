package com.marcela.service;

import com.marcela.model.Product;

public interface ProductService {

	public Iterable<Product> getListarProductos();
	public Product getBuscarProducto(int producto);	
}