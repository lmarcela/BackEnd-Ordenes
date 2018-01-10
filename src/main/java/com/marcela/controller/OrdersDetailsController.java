package com.marcela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcela.model.OrderDetail;
import com.marcela.service.OrderDetailService;

/**
 * Created by marcela
 */

@CrossOrigin
@RestController
public class OrdersDetailsController {

	@Autowired
	OrderDetailService orderDetailService;

	@RequestMapping(value = "/listarOrdenesDetalles", method = RequestMethod.GET)
	public ResponseEntity<Iterable<OrderDetail>> getListarOrdenesDetalles() {
		return new ResponseEntity<Iterable<OrderDetail>>(orderDetailService.getListarOrdenesDetalles(), HttpStatus.OK);
	}
}