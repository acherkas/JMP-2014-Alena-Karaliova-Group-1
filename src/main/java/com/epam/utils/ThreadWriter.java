package com.epam.utils;

import java.io.IOException;

import com.epam.model.Transaction;

	public	class ThreadWriter implements Runnable{
	    FileOperator fo;
	    Transaction transaction;

	    public ThreadWriter(FileOperator fo, Transaction transaction) {
	        super();
	        this.fo = fo;
	        this.transaction = transaction;
	    }
	    
	    public void run() {
	        synchronized (fo) {
	            try {
	                fo.writeInFile(transaction);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            fo.isWriteComplete = true;
	            fo.notify();
	        }
	    }

}
