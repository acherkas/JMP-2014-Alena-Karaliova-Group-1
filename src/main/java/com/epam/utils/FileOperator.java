package com.epam.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.epam.model.Transaction;

public class FileOperator {
	File file = new File("transactions.txt");

	BufferedWriter bw;
	BufferedReader br;
	boolean isWriteComplete = false;

	public FileOperator() throws IOException {
		if (!file.exists())
			file.createNewFile();
		bw = new BufferedWriter(new FileWriter(file, true));
		br = new BufferedReader(new FileReader(file));

	}

	//write to file completed transaction
	public void writeInFile(Transaction transaction) throws IOException {
		bw.append(transaction.getAccount().getName()
				+ ":"
				+ transaction.getCurrency().getName()
				+ ":"
				+ transaction.getCurrency().getPrice_blr()
				+ ":"
				+ transaction.getAmount().getAmount()
				+ ":"
				+ (transaction.getAmount().getAmount() * transaction
						.getCurrency().getPrice_blr()));
		bw.newLine(); 
		bw.flush(); //clearing buffer
	}

	//reads the last string with transaction
	public String readFromFile() throws IOException {
		String sCurrentLine;
		String lastLine = null;
		while ((sCurrentLine = br.readLine()) != null) {
			lastLine = sCurrentLine;
		}
		String segments[] = lastLine.split(":");
		lastLine = "Operation| account:"
				+ segments[0]
				+ " sell currency:"
				+ segments[1]
				+ " rate:"
				+ segments[2]
				+ " count:"
				+ segments[3]
				+ " result:"
				+ (Integer.parseInt(segments[2]) * Integer
						.parseInt(segments[3]));
		return lastLine;
	}
}