package com.marcela.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Created by marcela 
 */

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int customerId;

	private String name;

	private String email;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "customer_product", 
	joinColumns = { @JoinColumn(name = "customer_id") }, inverseJoinColumns = { @JoinColumn(name = "product_id") })
	private Set<Product> products = new HashSet<Product>(0);

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}