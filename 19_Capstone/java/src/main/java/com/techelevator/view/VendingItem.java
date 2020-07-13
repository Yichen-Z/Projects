package com.techelevator.view;

public abstract class VendingItem {

	private String code;
	private String name;
	private double price;
	private int count;
	private String category;

	public VendingItem(String code, String name, double price, String category) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.category = category;
		this.count = 5;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	public String getCategory() {
		return category;
	}
	public abstract String dispensingMessage();
	
	@Override
	public String toString() {
		// Tested manually by inspecting console printout and correcting format
		if(this.getCount() == 0)
		return String.format("%s: %s\t\t\t$%.2f\tSOLD OUT", this.getCode(),
				this.getName(), this.getPrice());
		
		return String.format("%s: %s\t\t\t$%.2f\tAvailable: %d", this.getCode(),
				this.getName(), this.getPrice(), this.getCount());
	
	}
	
}
