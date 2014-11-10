package com.epam.service;

import java.io.IOException;

import com.epam.model.Transaction;
import com.epam.utils.FileOperator;
import com.epam.utils.ThreadReader;
import com.epam.utils.ThreadWriter;

public class TransactionOperations {

	// save transaction in to file in separate thread and notify another thread
	// (consumer)
	public void saveTransaction(FileOperator fop,
			Transaction transaction) throws IOException {
		Thread tw = new Thread(new ThreadWriter(fop, transaction));
		tw.start();
	}

	// starting separate thread to read the file
	public void startReadTransaction(FileOperator fop)
			throws IOException {
		Thread tr = new Thread(new ThreadReader(fop));
		tr.start();
	}
	
}
