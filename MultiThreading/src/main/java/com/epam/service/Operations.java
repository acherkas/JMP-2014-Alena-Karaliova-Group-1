package com.epam.service;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.epam.model.Amount;
import com.epam.model.Transaction;
import com.epam.utils.FileOperator;

public class Operations {
	public Transaction transaction;

	public static void main(String[] args) throws IOException,
			InterruptedException {
		DataOperations dOp = new DataOperations();
		TransactionOperations trOp = new TransactionOperations();
		dOp.transaction = new Transaction();
		FileOperator fop = null;
		try {
			fop = new FileOperator();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ----MAIN MENU--------
		Scanner scanner = new Scanner(System.in);
		int selection = 0;
		int i = 0;
		while (i == 0) {
			System.out.println("Please choose an option from the following:");
			System.out.println("[1] Exchange currency");
			System.out.println("[2] View information");
			System.out.println("[3] Quit");
			System.out.println("Choice: ");
			selection = scanner.nextInt();

			switch (selection) {

			case 1:
				// starting separate thread to read the file
				trOp.startReadTransaction(fop);
				System.out
						.println("Enter your user and password (user:password):");
				String acc = scanner.next();
				if (dOp.checkAccount(acc)) {
					System.out
							.println("Choose currency that you want to sell:");
					// print list of currencies from file
					dOp.printCurrencyList();
					@SuppressWarnings("resource")
					Scanner input = new Scanner(System.in);
					int cur = input.nextInt();
					// check that currency present in the list
					if (dOp.setCheckedCurrency(cur)) {
						System.out.println("Inter amount:");
						String amm = input.next();
						// check that amount contains only numbers
						if (!Pattern.matches("[a-zA-Z]+", amm)) {
							dOp.transaction.setAmount(new Amount());
							dOp.transaction.getAmount().setAmount(
									Integer.parseInt(amm));

							// save transaction in to file in separate thread
							// and notify another thread (consumer)
							trOp.saveTransaction(fop, dOp.transaction);
							// for printing main menu after the operations
							// details
							Thread.sleep(500);
						}
					}
				} else {
					System.out.println("Account or password are incorrect");
				}
				break;
			// not implemented
			case 2:
				System.out.println("List of all transactions");
				dOp.readTransactions();
//				i = 1;
				break;

			case 3:
				System.out.println("Quiting...");
				scanner.close();
				System.exit(5);

			default:
				System.out.println("Your choice was not valid!");
			}
		}
	}

}
