package com.techelevator.view;

public class Gum extends VendingItem {

	public Gum(String code, String name, double price) {
		super(code, name, price, "Gum");

	}

	@Override
	public String dispensingMessage() {

		return "Chew Chew, Yum!";
	}

}
