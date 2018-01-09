package com.marcela.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcela.model.Customer;
import com.marcela.model.Data;
import com.marcela.model.Order;
import com.marcela.model.OrderDetail;
import com.marcela.model.Product;
import com.marcela.service.CustomerService;
import com.marcela.service.OrderDetailService;
import com.marcela.service.OrderService;
import com.marcela.service.ProductService;

/**
 * Created by marcela 
 */


@CrossOrigin
@RestController
public class OrdersController {

	@Autowired
	OrderService orderService;

	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	@Autowired
	OrderDetailService orderDetailService;
	
	@GetMapping("/orders")
	public List<Order> getOrders() {
		return orderService.getOrders();
	}
	
	@GetMapping("/order/{orderId}")
	public Order getOrder(@PathVariable int orderId) {
		return orderService.getOrder(orderId);
	}

	@GetMapping("/order/customer/{customerId}")
	public List<Order> getOrderCustomer(@PathVariable int customerId) {
		return orderService.getOrderCustomer(customerId);
	}

	@GetMapping("/order/customer/{customerId}/desde/{fechaInicio}/hasta/{fechaFin}")
	public List<Order> getOrderCustomerDates(@PathVariable int customerId, @PathVariable Date fechaInicio, @PathVariable Date fechaFin) {
		return orderService.getOrderCustomerDates(customerId,fechaInicio,fechaFin);
	}    

	@GetMapping("/order/customer/{customerId}/ultimoMes")
	public List<Order> getOrderCustomerLastMonth(@PathVariable int customerId) {
		LocalDate fechaActual = LocalDate.now();
		LocalDate ultimoMes =  fechaActual.minusMonths(1);
		return orderService.getOrderCustomerLastMonth(customerId,Date.valueOf(fechaActual),Date.valueOf(ultimoMes));
	} 

	@PostMapping("/order")
	public Order createOrder(@RequestBody Data data) {
		try {
		int customerId=data.getCustomerId();
		String deliveryAddress=data.getDeliveryAddress();
		String fecha=data.getCreationDate();
		int[] productIds=data.getProductIds();
		String[] productDescriptions = new String [productIds.length];
		double[] productPrices = new double[productIds.length];
		int[] productQuantitys=data.getProductQuantitys();
		int unidades=0;		
		Date creationDate = java.sql.Date.valueOf(fecha);
		for(int i=0;i<productQuantitys.length;i++) {
			unidades+=productQuantitys[i];
		}
		if(unidades==0) {
			//return "ERROR: No se puede crear orden si no hay productos";
			return null;
		}
		else if(unidades<6) {
			if(productIds.length==productQuantitys.length) {
				boolean esValido = true;
				for(int i=0;i<productIds.length&esValido;i++) {					
					Customer c=customerService.getCustomer(customerId);
					Product p =productService.getProduct(productIds[i]);
					if (c.getProducts().contains(p)) {
						productPrices[i] = p.getPrice();
						productDescriptions[i] = p.getName();
					} else {
						esValido = false;
					}
				}			
				if(esValido) {
					Order order = new Order();
					order.setCustomer(customerService.getCustomer(customerId));
					order.setDeliveryAddress(deliveryAddress);
					order.setCreationDate(creationDate);
					double total = 0.0;
					for(int i=0;i<productIds.length;i++) {
						total = total + (productPrices[i]*productQuantitys[i]);
					}
					order.setTotal(total);
					orderService.createOrder(order);
					System.out.println("Se creó order con id: "+order.getOrderId());
					for(int i=0;i<productIds.length;i++) {
						OrderDetail orderDetail = new OrderDetail();
						orderDetail.setOrder(orderService.getOrder(order.getOrderId()));
						orderDetail.setProduct(productService.getProduct(productIds[i]));
						orderDetail.setProductDescription(productDescriptions[i]);
						orderDetail.setPrice(productPrices[i]);
						orderDetail.setQuantity(productQuantitys[i]);
						orderDetailService.save(orderDetail);
						System.out.println("Se creó order_detail con id: "+orderDetail.getOrderDetailId());
					}
					//return "Se creo order con id "+order.getOrderId()+" del "+creationDate;
					return order;
				}
				else {
					//return "ERROR: Al menos uno de los productos que indico no estan permitidos para el cliente.";
					return null;
				}
			}else {
				//return "ERROR: Deben haber tantas cantidades como id de productos.";
				return null;
			}
		}
		else {
			//return "ERROR: Se supero el maximo de unidades permitidas. El maximo permitido es 5 y su orden tiene "+unidades+" unidades.";
			return null;
		}

	}
		catch (Exception e) {
			return null;
		}
}
	}