package model;

import java.io.Serializable;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	private int orderId;
	private int customerId;
	private String customerName;
	private int productId;
	private float amount;
	private String orderDate;
	
	public Order() {
	}

	public Order(int orderId, int customerId, String customerName, int productId, float amount, String orderDate) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.productId = productId;
		this.amount = amount;
		this.orderDate = orderDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", customerName=" + customerName
				+ ", productId=" + productId + ", amount=" + amount + ", orderDate=" + orderDate + "]";
	}
	
	
	
	
}
