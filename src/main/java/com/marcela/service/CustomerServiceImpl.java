package com.marcela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcela.model.Customer;
import com.marcela.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Iterable<Customer> getListarClientes() {		
		return customerRepository.findAll();
	}

	@Override
	public Customer getBuscarCliente(int cliente) {
		return customerRepository.findOne(cliente);
	}

}