package com.marcela.service;

import com.marcela.model.Customer;

public interface CustomerService {

	public Iterable<Customer> getListarClientes();
	public Customer getBuscarCliente(int cliente);	
}