package com.techelevator.view;

public class Candy extends VendingItem {

	public Candy(String code, String name, double price) {
		super(code, name, price, "Candy");

	}

	@Override
	public String dispensingMessage() {
		return "Munch Munch, Yum!";
	}

}
