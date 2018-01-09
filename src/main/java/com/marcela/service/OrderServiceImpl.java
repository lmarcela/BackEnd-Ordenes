package com.marcela.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcela.model.Order;
import com.marcela.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrder(int orderId) {
		return orderRepository.findOne(orderId);
	}

	@Override
	public List<Order> getOrderCustomer(int customerId) {
		return orderRepository.findByCustomer(customerId);
	}

	@Override
	public List<Order> getOrderCustomerDates(int customerId, Date fechaInicio, Date fechaFin) {
		return orderRepository.findByCustomerDates(customerId, fechaInicio, fechaFin);
	}

	@Override
	public List<Order> getOrderCustomerLastMonth(int customerId, Date fechaActual, Date ultimoMes) {
		return orderRepository.findByCustomerLastMonth(customerId, fechaActual, ultimoMes);
	}

	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

}