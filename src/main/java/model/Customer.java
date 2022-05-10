package model;

import java.io.Serializable;

public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	private int customerId;
	private String customerName;
	private String address;
	private int phone;
	
	
	
	public Customer() {
	}

	public Customer(int customerId, String customerName, String address, int phone) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.address = address;
		this.phone = phone;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", address=" + address
				+ ", phone=" + phone + "]";
	}
	
	
}
