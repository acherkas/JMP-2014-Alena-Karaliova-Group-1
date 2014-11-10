package com.epam.dataoperations;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.epam.models.Currency;
import com.epam.models.Transaction;
import com.epam.service.DataOperations;

public class DataOperationsTest {

	@Test
	public void testCheckCurrency() throws Exception {
		DataOperations dOp = new DataOperations();
		Transaction tr = new Transaction();
		dOp.setTransaction(tr);
		assertEquals(true,dOp.checkCurrency("usd"));
	}
	
	@Test
	public void testFillCurrencyList() throws Exception {
		DataOperations dOp = new DataOperations();
		dOp.fillCurrencyList();
		assertTrue(dOp.getCurrencyList().size()>0);
	}
	
	
	@Test
	public void testSetCheckedCurrency() throws Exception {
		DataOperations dOp = new DataOperations();
		Transaction tr = new Transaction();
		List<Currency> currencyList = new ArrayList<Currency>();
		Currency cur = new Currency();
		cur.setName("usd");
		cur.setPrice_blr(1241241);
		currencyList.add(cur);
		Currency cur2 = new Currency();
		cur2.setName("eur");
		cur2.setPrice_blr(1241241);
		currencyList.add(cur2);
		dOp.setCurrencyList(currencyList);
		dOp.setTransaction(tr);
		assertEquals(true,dOp.setCheckedCurrency(1));
	}
}
