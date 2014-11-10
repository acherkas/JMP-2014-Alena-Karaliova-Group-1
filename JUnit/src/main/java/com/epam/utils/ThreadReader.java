package com.epam.utils;

import java.io.IOException;

public class ThreadReader implements Runnable {
	FileOperator fo;

	public ThreadReader(FileOperator fo) {
		super();
		this.fo = fo;
	}
	
	public void run() {
		synchronized (fo) {
			if (!fo.isWriteComplete) {
				try {
					fo.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("--------OPERATION DETAILS------");
			try {
				System.out.println(fo.readFromFile());
				System.out.println("-------------------------------");
			} catch (IOException e) {
				e.printStackTrace();
			}
			fo.notify();
		}
	}
}
