package com.marcela.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcela.model.Customer;
import com.marcela.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getCustomers() {		
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomer(int customerId) {
		return customerRepository.findOne(customerId);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public boolean deleteCustomer(int customerId) {
		customerRepository.delete(customerId);
		return true;
	}

	@Override
	public boolean existCustomer(Customer customer) {
		return customerRepository.exists(customer.getCustomerId());
	}

}