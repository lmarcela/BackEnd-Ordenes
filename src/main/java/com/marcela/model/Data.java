package com.marcela.model;

public class Data {
	private int customerId;
	private String deliveryAddress;
	private String creationDate;
	private int []productIds;
	private String []productDescriptions;
	private double []productPrices;
	private int[]productQuantitys;

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public int[] getProductIds() {
		return productIds;
	}
	public void setProductIds(int[] productIds) {
		this.productIds = productIds;
	}
	public String[] getProductDescriptions() {
		return productDescriptions;
	}
	public void setProductDescriptions(String[] productDescriptions) {
		this.productDescriptions = productDescriptions;
	}
	public double[] getProductPrices() {
		return productPrices;
	}
	public void setProductPrices(double[] productPrices) {
		this.productPrices = productPrices;
	}
	public int[] getProductQuantitys() {
		return productQuantitys;
	}
	public void setProductQuantitys(int[] productQuantitys) {
		this.productQuantitys = productQuantitys;
	}

}