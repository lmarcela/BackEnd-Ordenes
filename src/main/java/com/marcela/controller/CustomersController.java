package com.marcela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcela.model.Customer;
import com.marcela.service.CustomerService;

/**
 * Created by marcela 
 */

@CrossOrigin
@RestController
public class CustomersController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/listarClientes",method = RequestMethod.GET)
	public ResponseEntity<Iterable<Customer>> getListarClientes() {
		return new ResponseEntity<Iterable<Customer>>(customerService.getListarClientes(), HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarCliente",method = RequestMethod.GET)
	public ResponseEntity<Customer> getBuscarCliente(int cliente) {
		return new ResponseEntity<Customer>(customerService.getBuscarCliente(cliente), HttpStatus.OK);
	}

}