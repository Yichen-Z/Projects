package com.techelevator.view;

public class Transaction {

	private double moneyProvided;
	private static final int[] COIN_DENOMINATIONS = { 25, 10, 5 };
	private static double totalSales;
	
	public Transaction(double amount) {
		this.moneyProvided = amount;
	}
	
	public double getMoneyProvided() {
		return moneyProvided;
	}
	
	public void addMoney(double amountFed) {
		moneyProvided += amountFed;
	}
	
	public void deductMoney(double cost) {
		moneyProvided -= cost;
		totalSales += cost;
	}
	
	public void resetMoney() {
		moneyProvided = 0;
	}
	
	// Give change in quarters, dimes, nickels in that order
	// Must be done before resetMoney
	public int[] giveChange() {
		int[] coins = new int[COIN_DENOMINATIONS.length];
		int cents = (int)(moneyProvided * 100);

		for (int i = 0; i < COIN_DENOMINATIONS.length; i++) {
			coins[i] = cents / COIN_DENOMINATIONS[i];
			cents = cents % COIN_DENOMINATIONS[i];
		}
		
		return coins;
	}
}
