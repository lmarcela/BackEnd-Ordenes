package com.marcela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcela.model.OrderDetail;
import com.marcela.repository.OrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public Iterable<OrderDetail> getListarOrdenesDetalles() {	
		return orderDetailRepository.findAll();
	}

	@Override
	public void save(OrderDetail orderDetail) {
		orderDetailRepository.save(orderDetail);
	}

}