package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class MoneyMenu extends Menu {

	private Transaction transaction;

	// Manually checked when running Vending Machine

	public MoneyMenu(InputStream input, OutputStream output, Transaction transaction) {
		super(input, output);
		this.transaction = transaction;
	}

	public double getMoneyFromUserInput() {

		while (true) {
			System.out.println("Please add money to make purchase: $1, $2, $5, $10, and $20 bills only.");
			System.out.print("$ ");
			// Try with resources here would close entire input stream
			Scanner moneyScanner = new Scanner(System.in);
			double amountAdded = 0;
			try {
				amountAdded = Double.parseDouble(moneyScanner.nextLine());
				if (amountAdded != (int) amountAdded) {
					System.out.println("Oops. Whole bills only please.");
					continue;
				} else if ((int) amountAdded != 20 && (int) amountAdded != 10 && (int) amountAdded != 5
						&& (int) amountAdded != 2 && (int) amountAdded != 1) {
					System.out.println("$1, $2, $5, $10, or $20 only please.");
					continue;
				}
				// Once verified, add to current money provided
				transaction.addMoney(amountAdded);
				return amountAdded;
			} catch (NumberFormatException ex) {
				System.out.println("Not a valid number. Please try again.");
			}
		}

	}

	@Override
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}
	
	@Override
	public Object getPurchaseMenuChoice(Object[] options, Transaction transaction) {
		Object choice = null;
		while (choice == null) {
			displayPurchaseMenuOptions(options, transaction);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {

		Scanner in = new Scanner(System.in);
		String userInput = in.nextLine();
		for (Object item : options) {
			VendingItem snack = (VendingItem)item;
			String code = snack.getCode();
			if (userInput.equalsIgnoreCase(code)) {
				if (snack.getCount() == 0) {
					System.out.println(snack.getName() + " is SOLD OUT.");
					return null; // Returns to Purchase Menu
				}
				return snack; // Return VendingItem
			}
		}
		System.out.println("Sorry that is not a valid item code, please re-enter a code.");
		return null;
	}

	private void displayMenuOptions(Object[] itemsAndQuantities) {
		System.out.println();
		for (int i = 0; i < itemsAndQuantities.length; i++) {
			System.out.println(itemsAndQuantities[i].toString());
		}
		
		System.out.print("\nPlease choose an product code >>> ");
		System.out.flush();
	}
	
	private void displayPurchaseMenuOptions(Object[] options, Transaction currentTransaction) {
		System.out.println();
		for (int i = 0; i < options.length; i++) {
			System.out.println(options[i].toString());
		}
		// Need to figure out bank class
		System.out.printf("\nCurrent money provided: $%.2f", currentTransaction.getMoneyProvided());
		System.out.print("\nPlease choose an option >>> ");
		System.out.flush();
	}

}
