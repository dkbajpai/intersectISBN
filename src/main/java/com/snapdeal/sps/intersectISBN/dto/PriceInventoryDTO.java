package com.snapdeal.sps.intersectISBN.dto;

public class PriceInventoryDTO {

	String price;
	String inventory;
	
	@Override
	public String toString() {
		return "PriceInventoryDTO [price=" + price + ", inventory=" + inventory
				+ "]\n";
	}
	public PriceInventoryDTO(String price, String inventory) {
		super();
		this.price = price;
		this.inventory = inventory;
	}
	public PriceInventoryDTO() {
		super();
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	
	
	
	
}
