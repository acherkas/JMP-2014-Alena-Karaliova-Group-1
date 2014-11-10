package com.epam.mock;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import com.epam.service.IDataOperations;
import com.epam.service.Operations;

public class ExceptionsDataOperationsTest {
	
	private final boolean USER_NOT_EXISTS = false;
	
	@Test(expected = Exception.class) 
	public void testRuntimeException() throws Exception {
		IDataOperations dataOperations = EasyMock
				.createMock(IDataOperations.class);
		Operations.setdOp(dataOperations);
		EasyMock.expect(dataOperations.checkAccount("user1:123")).andThrow(new RuntimeException());
		EasyMock.replay(dataOperations);
		assertEquals(USER_NOT_EXISTS, Operations.checkAccountForPresence("user1:123"));
		EasyMock.verify(dataOperations);

	}

	@Test(expected = Exception.class) 
	public void testNullPointerException() throws Exception {
		IDataOperations dataOperations = EasyMock
				.createMock(IDataOperations.class);
		Operations.setdOp(dataOperations);
		EasyMock.expect(dataOperations.checkAccount("user1:123")).andThrow(new NullPointerException());
		EasyMock.replay(dataOperations);
		assertEquals(USER_NOT_EXISTS, Operations.checkAccountForPresence("user1:123"));
		EasyMock.verify(dataOperations);

	}
}
