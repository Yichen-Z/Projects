package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}


	
//	This is just for the main menu
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	// Get Purchase Menu Choice and display correct menu
	public Object getPurchaseMenuChoice(Object[] options, Transaction transaction) {
		Object choice = null;
		while (choice == null) {
			displayPurchaseMenuOptions(options, transaction);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
//			System.out.println("Sorry " + userInput + " does not exist, please enter another choice");// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}

	// Only good for Main Menu
	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
			// Something here is breaking the MenuTest??? Wonky!
		}
		System.out.println();
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}

	//
	private void displayPurchaseMenuOptions(Object[] options, Transaction currentTransaction) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		// Need to figure out bank class
		out.printf("\nCurrent money provided: $%.2f", currentTransaction.getMoneyProvided());
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}

	// To return from Display Vending Machine Items to Main Menu
	public void backToMainMenu(VendingItem[] itemsAndQuantities) {
		out.println();
		for (int i = 0; i < itemsAndQuantities.length; i++) {
			int count = itemsAndQuantities[i].getCount();
			String available = Integer.toString(count);
			if (count == 0) {
				available = "SOLD OUT";
			}
			out.printf("%s: %s available\n", itemsAndQuantities[i].getName(), available);
		}
		out.flush();
	}
}
