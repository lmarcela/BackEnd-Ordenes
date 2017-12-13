package com.marcela.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	/*@RequestMapping(value = "/listarClientes",method = RequestMethod.GET)
	public ResponseEntity<Iterable<Customer>> getListarClientes() {
		return new ResponseEntity<Iterable<Customer>>(customerService.getListarClientes(), HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarCliente",method = RequestMethod.GET)
	public ResponseEntity<Customer> getBuscarCliente(int cliente) {
		return new ResponseEntity<Customer>(customerService.getBuscarCliente(cliente), HttpStatus.OK);
	}*/
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}

	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable int customerId){
		return customerService.getCustomer(customerId);
	}

	@PostMapping("/customer")
	public Customer createCustomer(@RequestBody Customer customer){
		return customerService.createCustomer(customer);
	}

	@PutMapping("/customer")
	public Customer updateCustomer(@RequestBody Customer customer){
		return customerService.updateCustomer(customer);
	}

	@DeleteMapping("/customer/{customerId}")
	public boolean deleteUser(@PathVariable int customerId){
		return customerService.deleteCustomer(customerId);
	}

}