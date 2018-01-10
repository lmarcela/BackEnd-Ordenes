package com.marcela.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Created by marcela
 */

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int productId;

	@Column(unique = true)
	private String name;

	private double price;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void addOrderDetails(OrderDetail orderDetail) {
		this.orderDetails.add(orderDetail);
	}

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Product() {
	}

}