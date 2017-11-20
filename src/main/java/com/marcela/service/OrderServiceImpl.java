package com.marcela.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcela.model.Order;
import com.marcela.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Iterable<Order> getListarOrdenes() {
		return orderRepository.findAll();
	}

	@Override
	public Iterable<Order> getBuscarOrdenesCliente(int cliente) {
		return orderRepository.findByCustomer(cliente);
	}

	@Override
	public Iterable<Order> getBuscarOrdenesClienteFechas(int cliente, Date fechaInicio, Date fechaFin) {
		return orderRepository.findByCustomerFechas(cliente, fechaInicio, fechaFin);
	}

	@Override
	public Iterable<Order> findByCustomerUltimoMes(int cliente, Date fechaActual, Date ultimoMes) {
		return orderRepository.findByCustomerUltimoMes(cliente, fechaActual, ultimoMes);
	}

	@Override
	public void save(Order order) {
		orderRepository.save(order);		
	}

	@Override
	public Order getOrder(int order) {
		return orderRepository.findOne(order);
	}

}