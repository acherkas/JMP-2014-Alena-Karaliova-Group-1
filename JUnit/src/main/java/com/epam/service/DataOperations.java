package com.epam.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.epam.models.Account;
import com.epam.models.Currency;
import com.epam.models.Transaction;
import com.epam.utils.exceptions.DataSourceException;
import com.epam.utils.exceptions.FileAccessException;

public class DataOperations implements IDataOperations {
	
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Transaction transaction;
	
	List<Currency> currencyList = new ArrayList<Currency>();
	
	// check that account and password are correct
		public boolean checkAccount(String accAndPass) {
			File file = new File("accounts.txt");
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				throw new FileAccessException(e);
			}
			String s;
			boolean isPresent = false;
			try {
				while ((s = br.readLine()) != null) {
					if (accAndPass.equalsIgnoreCase(s)) {
						String segments[] = accAndPass.split(":");
						Account acc = new Account();
						acc.setName(segments[0]);
						transaction.setAccount(acc); //set account in transaction model
						isPresent = true;
					}
				}
			} catch (IOException e) {
				throw new DataSourceException(e);
			}
			try {
				br.close();
			} catch (IOException e) {
				throw new DataSourceException(e);
			}
			return isPresent;
		}

		public void readUsers() {
			File file = new File("accounts.txt");
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				throw new FileAccessException(e);
			}
			String s;
			String[] temp = null;
			try {
				while ((s = br.readLine()) != null) {
					temp = s.toString().split("\n");
					for (int i = 0; i < temp.length; i++) {
						System.out.println(temp[i]);
						
					}
				}
			} catch (IOException e) {
				throw new DataSourceException(e);
			}
			try {
				br.close();
			} catch (IOException e) {
				throw new DataSourceException(e);
			}
		}
		
		
		public void readTransactions()  {
			File file = new File("transactions.txt");
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				throw new FileAccessException(e);
			}
			String s;
			try {
				while ((s = br.readLine()) != null) {
						System.out.println(s);
				}
			} catch (IOException e) {
				throw new DataSourceException(e);
			}
			try {
				br.close();
			} catch (IOException e) {
				throw new DataSourceException(e);
			}
		}
		
		
		public boolean checkCurrency(String currency) {
			File file = new File("rates.txt");
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				throw new FileAccessException(e);
			}
			String s;
			boolean isPresent = false;
			try {
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
			} catch (IOException e) {
				throw new DataSourceException(e);
			}
			try {
				br.close();
			} catch (IOException e) {
				throw new DataSourceException(e);
			}
			return isPresent;
		}
		
		
		// fill List with currencies from file
		public void fillCurrencyList() throws IOException {
			File file = new File("rates.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s;
			currencyList.clear();
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

		public List<Currency> getCurrencyList() {
			return currencyList;
		}
		
		public void setCurrencyList(List<Currency> currencyList) {
			this.currencyList = currencyList;
		}

}
