package com.epam.service;

import java.io.IOException;

import com.epam.models.Transaction;
import com.epam.utils.FileOperator;
import com.epam.utils.ThreadReader;
import com.epam.utils.ThreadWriter;

public class TransactionOperations {

	// save transaction in to file in separate thread and notify another thread
	// (consumer)
	/* (non-Javadoc)
	 * @see com.epam.service.ItransactionOperations#saveTransaction(com.epam.utils.FileOperator, com.epam.model.Transaction)
	 */
	public void saveTransaction(FileOperator fop,
			Transaction transaction) throws IOException {
		Thread tw = new Thread(new ThreadWriter(fop, transaction));
		tw.start();
	}

	// starting separate thread to read the file
	/* (non-Javadoc)
	 * @see com.epam.service.ItransactionOperations#startReadTransaction(com.epam.utils.FileOperator)
	 */
	public void startReadTransaction(FileOperator fop)
			throws IOException {
		Thread tr = new Thread(new ThreadReader(fop));
		tr.start();
	}
	
}
