package com.epam.dataoperations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.epam.service.DataOperations;

public class ConsoleContentTest {
	@Test
	public void testReadUsers() throws Exception {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		DataOperations dOp = new DataOperations();
		dOp.readUsers();
		String whatWasPrinted = new String(outContent.toByteArray());
		String[] linesOfOutput = whatWasPrinted.split("\n");
		assertNotNull(linesOfOutput.length);
		assertEquals(2, linesOfOutput.length);
		System.setOut(null);
		System.setErr(null);
	}
	
	@Test
	public void testReadTransactions() throws Exception {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		DataOperations dOp = new DataOperations();
		dOp.readTransactions();
		String whatWasPrinted = new String(outContent.toByteArray());
		String[] linesOfOutput = whatWasPrinted.split("\n");
		assertNotNull(linesOfOutput.length);
		System.setOut(null);
		System.setErr(null);
	}
	

	@Test
	public void testPrintCurrencyList() throws Exception {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		DataOperations dOp = new DataOperations();
		dOp.printCurrencyList();
		String whatWasPrinted = new String(outContent.toByteArray());
		String[] linesOfOutput = whatWasPrinted.split("\n");
		assertNotNull(linesOfOutput.length);
		System.setOut(null);
		System.setErr(null);
	}
}
