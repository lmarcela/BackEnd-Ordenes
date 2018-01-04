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

	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		return customerService.getCustomer(customerId);
	}

	@PostMapping("/customer")
	public Customer createCustomer(@RequestBody Customer customer) {
		System.out.println("LLEGO a crear");
		System.out.println(customer.getName()+ "productos: "+customer.getProducts());
		try {
			System.out.println("gUARDO");
			return customerService.createCustomer(customer);
		} catch (Exception e) {
			System.out.println("NO gUARDO"+e.getMessage());
			return null;
		}
	}

	@PutMapping("/customer")
	public Customer updateCustomer(@RequestBody Customer customer) {
		System.out.println("LLEGO a ACTUALIZAR");
		System.out.println(customer.getName()+ "productos: "+customer.getProducts());
		if (customerService.existCustomer(customer)) {
			return customerService.updateCustomer(customer);
		} else {
			return null;
		}
	}

	@DeleteMapping("/customer/{customerId}")
	public boolean deleteUser(@PathVariable int customerId) {
		try {
			return customerService.deleteCustomer(customerId);
		} catch (Exception e) {
			return false;
		}
	}

}