package com.marcela.service;

import com.marcela.model.OrderDetail;

public interface OrderDetailService {

	public Iterable<OrderDetail> getListarOrdenesDetalles();
	public void save(OrderDetail orderDetail);
}