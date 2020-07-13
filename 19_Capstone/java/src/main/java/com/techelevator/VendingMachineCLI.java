package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.*;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_DISPLAY_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_DISPLAY_EXIT };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT = "Select Product";
	private static final String PURCHASE_MENUE_OPTION_FINISH = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT, PURCHASE_MENUE_OPTION_FINISH };

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public static List<String[]> readInventory(String filePath) throws FileNotFoundException {
		File inventory = new File(filePath);
		List<String[]> vendingSupplies = new ArrayList<>();

		try (Scanner fileReader = new Scanner(inventory)) {
			// Establish a list to take care of the data coming in
			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine().trim();
				String[] item = line.split("\\|");
				vendingSupplies.add(item);
			}
		}
		return vendingSupplies;
	}

	public void run() throws Exception {
		// Starts up - import inventory into a String array
		String path = "vendingmachine.csv";
		List<String[]> readItems = readInventory(path);

		VendingItem[] supply = new VendingItem[readItems.size()];
		int i = 0;
		for (String[] item : readItems) {
			// Parse read[2] from String into double price
			double itemPrice = Double.parseDouble(item[2]);

			if (item[3].equalsIgnoreCase("Chip")) {
				Chip nextChip = new Chip(item[0], item[1], itemPrice);
				supply[i++] = nextChip;
			} else if (item[3].equalsIgnoreCase("Candy")) {
				Candy nextCandy = new Candy(item[0], item[1], itemPrice);
				supply[i++] = nextCandy;
			} else if (item[3].equalsIgnoreCase("Drink")) {
				Drink nextDrink = new Drink(item[0], item[1], itemPrice);
				supply[i++] = nextDrink;
			} else if (item[3].equalsIgnoreCase("Gum")) {
				Gum nextGum = new Gum(item[0], item[1], itemPrice);
				supply[i++] = nextGum;
			}
		}

		// Tested Main Menu steps through user input (each number took us to the right place)
		// Verified results on console
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				menu.backToMainMenu(supply);
				continue;
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				Transaction customerTransaction = new Transaction(0.0);
				while (true) {
					// Tested for allowable inputs manually
					// Tested for correct screen display manually
					String purchaseChoice = (String) menu.getPurchaseMenuChoice(PURCHASE_MENU_OPTIONS,
							customerTransaction);
					if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						MoneyMenu customerDeposit = new MoneyMenu(System.in, System.out, customerTransaction);
						double deposit = customerDeposit.getMoneyFromUserInput();
						auditLog(String.format("FEED MONEY:  $%.2f $%.2f", deposit,
								customerTransaction.getMoneyProvided()));

					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT)) {
//						this is where they buy stuff
						// Decrement item they buy - checked that this works manually
						// Confirmed correct output on text log
						MoneyMenu customerChoice = new MoneyMenu(System.in, System.out, customerTransaction);
						VendingItem snackChoice = null;
						while (snackChoice == null) {
							snackChoice = (VendingItem) customerChoice.getPurchaseMenuChoice(supply,
									customerTransaction);
							if (customerTransaction.getMoneyProvided() >= snackChoice.getPrice()) {
								System.out.printf("\n%s: $%.2f\n", snackChoice.getName(), snackChoice.getPrice());
								double previousBalance = customerTransaction.getMoneyProvided();
								// Takes out cost of snack
								customerTransaction.deductMoney(snackChoice.getPrice());
								System.out.printf("Remaining Balance:  $%.2f\n",
										customerTransaction.getMoneyProvided());
								auditLog(
										String.format("%s %s $%.2f $%.2f", snackChoice.getName(), snackChoice.getCode(),
												previousBalance, customerTransaction.getMoneyProvided()));

								int previousCount = snackChoice.getCount();
								snackChoice.setCount(previousCount - 1);

								// Dispensing message
								System.out.println(snackChoice.dispensingMessage());
							} else {
								System.out.println("Please add more money.");
							}
						}
						/*
						 * Tested for correct display, change, money format manually
						 * Also plan to test Transaction Class with separate unit tests
						 */

					} else if (purchaseChoice.equals(PURCHASE_MENUE_OPTION_FINISH)) {
						int[] coinsChange = customerTransaction.giveChange();
						double previousBalance = customerTransaction.getMoneyProvided();
						String change = String.format("Here's your change:\n%d quarters\n%d dimes\n%d nickels",
								coinsChange[0], coinsChange[1], coinsChange[2]);
						customerTransaction.resetMoney();
						auditLog (String.format("GIVE CHANGE: $%.2f $%.2f", previousBalance, customerTransaction.getMoneyProvided())); 
						System.out.printf("\n%s\nCurrent balance: $%.2f\n", change,
								customerTransaction.getMoneyProvided());
						break;
					}
				}

			} else if (choice.equals(MAIN_MENU_OPTION_DISPLAY_EXIT)) {
				// Checked that this works manually
				System.exit(0);
			}
		}
	}

	public static void auditLog(String message) throws Exception {
		// Checked this manually by inspecting the log after each auditLog addition
		// in the purchase code.
		// Then we cleared the log for final project
		try (Logger log = new Logger("Log.txt")) {
			log.write(LocalDate.now() + " " + LocalTime.now() + " " + message);

		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI ourVendingMachine;
		try {
			ourVendingMachine = new VendingMachineCLI(menu);
			ourVendingMachine.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
