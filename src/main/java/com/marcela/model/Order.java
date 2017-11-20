package com.marcela.model;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.marcela.repository.OrderDetailRepository;


/**
 * Created by marcela
 */

@Entity
@Table(name = "Orders")
public class Order {
	@Autowired
	@Transient
	OrderDetailRepository orderDetailRepository;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int orderId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId")
	private Customer customer;

	private String deliveryAddress;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date creationDate;

	private double total;

	@OneToMany(mappedBy = "order")
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}		

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void addOrderDetails(OrderDetail orderDetail) {
		this.orderDetails.add(orderDetail);
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public int getCustomerId() {
		return customer.getCustomerId();
	}

	public String getCustomerName() {
		return customer.getName();
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}  

}