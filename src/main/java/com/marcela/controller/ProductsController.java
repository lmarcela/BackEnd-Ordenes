package com.marcela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/listarProductos",method = RequestMethod.GET)
	public ResponseEntity<Iterable<Product>> getListarProductos() {
		return new ResponseEntity<Iterable<Product>>(productService.getListarProductos(), HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarProducto",method = RequestMethod.GET)
	public ResponseEntity<Product> getBuscarProducto(int producto) {
		return new ResponseEntity<Product>(productService.getBuscarProducto(producto), HttpStatus.OK);
	}

}