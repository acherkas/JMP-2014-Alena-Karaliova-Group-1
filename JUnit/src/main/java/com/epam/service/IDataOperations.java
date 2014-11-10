package com.epam.service;

import java.io.IOException;

import com.epam.models.Transaction;

public interface IDataOperations {

	public abstract Transaction getTransaction();

	public abstract void setTransaction(Transaction transaction);

	// check that account and password are correct
	/* (non-Javadoc)
	 * @see com.epam.service.IDataOperation#checkAccount(java.lang.String)
	 */
	public abstract boolean checkAccount(String accAndPass);

	/* (non-Javadoc)
	 * @see com.epam.service.IDataOperation#readUsers()
	 */
	public abstract void readUsers() throws IOException;

	/* (non-Javadoc)
	 * @see com.epam.service.IDataOperation#readTransactions()
	 */
	public abstract void readTransactions() throws IOException;

	/* (non-Javadoc)
	 * @see com.epam.service.IDataOperation#checkCurrency(java.lang.String)
	 */
	public abstract boolean checkCurrency(String currency) throws IOException;

	// fill List with currencies from file
	/* (non-Javadoc)
	 * @see com.epam.service.IDataOperation#fillCurrencyList()
	 */
	public abstract void fillCurrencyList() throws IOException;

	// print to console the full list of the currencies
	/* (non-Javadoc)
	 * @see com.epam.service.IDataOperation#printCurrencyList()
	 */
	public abstract void printCurrencyList() throws IOException;

	// check that currency present in the list
	/* (non-Javadoc)
	 * @see com.epam.service.IDataOperations#setCheckedCurrency(int)
	 */
	/* (non-Javadoc)
	 * @see com.epam.service.IDataOperation#setCheckedCurrency(int)
	 */
	public abstract boolean setCheckedCurrency(int curr);

}