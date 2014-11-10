package com.epam.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.epam.model.Account;
import com.epam.model.Currency;

public class DataOperations extends Operations{
	List<Currency> currencyList = new ArrayList<Currency>();
	
	// check that account and password are correct
		public boolean checkAccount(String accAndPass) throws IOException {
			File file = new File("accounts.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s;
			boolean isPresent = false;
			while ((s = br.readLine()) != null) {
				if (accAndPass.equalsIgnoreCase(s)) {
					String segments[] = accAndPass.split(":");
					Account acc = new Account();
					acc.setName(segments[0]);
					transaction.setAccount(acc); //set account in transaction model
					isPresent = true;
				}
			}
			br.close();
			return isPresent;
		}

		public void readUsers() throws IOException {
			File file = new File("accounts.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s;
			String[] temp = null;
			while ((s = br.readLine()) != null) {
				temp = s.toString().split("\n");
				for (int i = 0; i < temp.length; i++) {
					System.out.println(temp[i]);
				}
			}
			br.close();
		}
		
		public void readTransactions() throws IOException {
			File file = new File("transactions.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s;
			while ((s = br.readLine()) != null) {
					System.out.println(s);
			}
			br.close();
		}
		
		public boolean checkCurrency(String currency) throws IOException {
			File file = new File("rates.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s;
			boolean isPresent = false;
			while ((s = br.readLine()) != null) {
				if (s.contains(currency)) {
					String segments[] = s.split(":");
					String cu = segments[0];
					if (currency.equalsIgnoreCase(cu)) {
						Currency cur = new Currency();
						cur.setName(segments[0]);
						cur.setPrice_blr(Integer.parseInt(segments[1]));
						transaction.setCurrency(cur);
						isPresent = true;
					}
				}
			}
			br.close();
			return isPresent;
		}
		
		// fill List with currencies from file
		public void fillCurrencyList() throws IOException {
			File file = new File("rates.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s;
			while ((s = br.readLine()) != null) {
				Currency cur = new Currency();
				cur.setName(s.split(":")[0]);
				cur.setPrice_blr(Integer.parseInt(s.split(":")[1]));
				currencyList.add(cur);
			}
			br.close();
		}

		// print to console the full list of the currencies
		public void printCurrencyList() throws IOException {
			fillCurrencyList();
			for (int i = 0; i < currencyList.size(); i++) {
				System.out.println("[" + i + "]" + currencyList.get(i).getName());
			}
		}

		// check that currency present in the list
		public boolean setCheckedCurrency(int curr) {
			boolean isSet = false;
			if (curr <= currencyList.size()) {
				Currency currency = new Currency();
				currency.setName(currencyList.get(curr).getName());
				currency.setPrice_blr(currencyList.get(curr).getPrice_blr());
				transaction.setCurrency(currency);//set currency in transaction model
				isSet = true;
			}
			return isSet;
		}

}
