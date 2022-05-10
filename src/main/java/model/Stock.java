package model;

public class Stock {

	private int productId;
	private int quantity;
	private int shopNo;
	
	public Stock() {
	}

	public Stock(int productId, int quantity, int shopNo) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.shopNo = shopNo;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}
	
	
	
}
