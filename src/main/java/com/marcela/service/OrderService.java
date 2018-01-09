package com.marcela.service;

import java.sql.Date;
import java.util.List;

import com.marcela.model.Order;

public interface OrderService {

	public List<Order> getOrders();

	public Order getOrder(int orderId);

	public List<Order> getOrderCustomer(int customerId);

	public List<Order> getOrderCustomerDates(int customerId, Date fechaInicio, Date fechaFin);

	public List<Order> getOrderCustomerLastMonth(int customerId, Date fechaActual, Date ultimoMes);

	public Order createOrder(Order order);
}