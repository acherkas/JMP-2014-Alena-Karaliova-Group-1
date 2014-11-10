package com.epam.mock;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import com.epam.service.IDataOperations;
import com.epam.service.Operations;

public class DataOperationsTest {
	
	private final boolean USER_EXISTS = true;
	
	private final boolean USER_NOT_EXISTS = false;
	
	@Test
	public void testThatUserExists() throws Exception {
		IDataOperations dataOperations = EasyMock
				.createMock(IDataOperations.class);
		Operations.setdOp(dataOperations);
		EasyMock.expect(dataOperations.checkAccount("user1:123")).andReturn(
				USER_EXISTS);
		EasyMock.replay(dataOperations);
		assertEquals(USER_EXISTS, Operations.checkAccountForPresence("user1:123"));
		EasyMock.verify(dataOperations);

	}
	
	@Test
	public void testThatUserNotExists() throws Exception {
		IDataOperations dataOperations = EasyMock
				.createMock(IDataOperations.class);
		Operations.setdOp(dataOperations);
		EasyMock.expect(dataOperations.checkAccount("user1:123")).andReturn(
				USER_NOT_EXISTS);
		EasyMock.replay(dataOperations);
		assertEquals(USER_NOT_EXISTS, Operations.checkAccountForPresence("user1:123"));
		EasyMock.verify(dataOperations);

	}

}
