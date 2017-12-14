package com.marcela.service;

import java.util.List;

import com.marcela.model.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();
	public Customer getCustomer(int customerId);	
	public Customer createCustomer(Customer customer);	
	public Customer updateCustomer(Customer customer);	
	public boolean deleteCustomer(int customerId);	
	public boolean existCustomer(Customer customer);	
}