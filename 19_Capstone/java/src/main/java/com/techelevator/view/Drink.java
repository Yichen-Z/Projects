package com.techelevator.view;

public class Drink extends VendingItem {

	public Drink(String code, String name, double price) {
		super(code, name, price, "Drink");
		
	}

	@Override
	public String dispensingMessage() {
		return "Glug Glug, Yum!";
	}

}
