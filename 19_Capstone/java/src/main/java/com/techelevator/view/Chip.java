package com.techelevator.view;

public class Chip extends VendingItem {

	public Chip(String code, String name, double price) {
		super(code, name, price, "Chip");

	}
	@Override
	public String dispensingMessage() {
	return "Crunch Crunch, Yum!";
}
}