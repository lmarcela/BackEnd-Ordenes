package com.marcela.service;

import java.sql.Date;

import com.marcela.model.Order;

public interface OrderService {

	public Iterable<Order> getListarOrdenes();
	public Iterable<Order> getBuscarOrdenesCliente(int cliente);	
	public Iterable<Order> getBuscarOrdenesClienteFechas(int cliente,Date fechaInicio,Date fechaFin);	
	public Iterable<Order> findByCustomerUltimoMes(int cliente,Date fechaActual,Date ultimoMes);
	public void save(Order order);
	public Order getOrder(int order);
}