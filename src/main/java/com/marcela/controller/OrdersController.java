package com.marcela.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@RequestMapping(value = "/listarOrdenes",method = RequestMethod.GET)
	public ResponseEntity<Iterable<Order>> getListarOrdenes() {
		return new ResponseEntity<Iterable<Order>>(orderService.getListarOrdenes(), HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarOrdenesCliente",method = RequestMethod.GET)
	public ResponseEntity<Iterable<Order>> getBuscarOrdenesCliente(int cliente) {
		return new ResponseEntity<Iterable<Order>>(orderService.getBuscarOrdenesCliente(cliente), HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarOrdenesClienteFechas",method = RequestMethod.GET)
	public ResponseEntity<Iterable<Order>> getBuscarOrdenesClienteFechas(int cliente,Date fechaInicio,Date fechaFin) {
		return new ResponseEntity<Iterable<Order>>(orderService.getBuscarOrdenesClienteFechas(cliente, fechaInicio, fechaFin), HttpStatus.OK);
	}    

	@RequestMapping(value = "/buscarOrdenesClienteUltimoMes",method = RequestMethod.GET)
	public ResponseEntity<Iterable<Order>> getBuscarOrdenesClienteUltimoMes(int cliente) {
		LocalDate fechaActual = LocalDate.now();
		LocalDate ultimoMes =  fechaActual.minusMonths(1);
		return new ResponseEntity<Iterable<Order>>(orderService.findByCustomerUltimoMes(cliente, Date.valueOf(fechaActual),Date.valueOf(ultimoMes)), HttpStatus.OK);
	}

	@RequestMapping(value="/createorder", method = RequestMethod.POST)   
	@ResponseBody
	public String saveOrder(@RequestBody Data data){
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
			return "ERROR: No se puede crear orden si no hay productos";
		}
		else if(unidades<6) {
			if(productIds.length==productQuantitys.length) {
				boolean esValido = true;
				for(int i=0;i<productIds.length&esValido;i++) {					
					Customer c=customerService.getBuscarCliente(customerId);
					Product p =productService.getBuscarProducto(productIds[i]);
					if (c.getProducts().contains(p)) {
						productPrices[i] = p.getPrice();
						productDescriptions[i] = p.getName();
					} else {
						esValido = false;
					}
				}			
				if(esValido) {
					Order order = new Order();
					order.setCustomer(customerService.getBuscarCliente(customerId));
					order.setDeliveryAddress(deliveryAddress);
					order.setCreationDate(creationDate);
					double total = 0.0;
					for(int i=0;i<productIds.length;i++) {
						total = total + (productPrices[i]*productQuantitys[i]);
					}
					order.setTotal(total);
					orderService.save(order);
					System.out.println("Se creó order con id: "+order.getOrderId());
					for(int i=0;i<productIds.length;i++) {
						OrderDetail orderDetail = new OrderDetail();
						orderDetail.setOrder(orderService.getOrder(order.getOrderId()));
						orderDetail.setProduct(productService.getBuscarProducto(productIds[i]));
						orderDetail.setProductDescription(productDescriptions[i]);
						orderDetail.setPrice(productPrices[i]);
						orderDetail.setQuantity(productQuantitys[i]);
						orderDetailService.save(orderDetail);
						System.out.println("Se creó order_detail con id: "+orderDetail.getOrderDetailId());
					}
					return "Se creo order con id "+order.getOrderId()+" del "+creationDate;
				}
				else {
					return "ERROR: Al menos uno de los productos que indico no estan permitidos para el cliente.";
				}
			}else {
				return "ERROR: Deben haber tantas cantidades como id de productos.";
			}
		}
		else {
			return "ERROR: Se supero el maximo de unidades permitidas. El maximo permitido es 5 y su orden tiene "+unidades+" unidades.";
		}

	}
}