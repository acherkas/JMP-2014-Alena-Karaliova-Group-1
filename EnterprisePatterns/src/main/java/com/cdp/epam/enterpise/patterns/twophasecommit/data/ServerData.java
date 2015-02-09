package com.cdp.epam.enterpise.patterns.twophasecommit.data;

import java.util.List;

public class ServerData {
	public boolean closed;
	public boolean inputFromAll;
	public List<ClientThread> t;
	public List<String> data;

	public ServerData(boolean closed, boolean inputFromAll) {
		this.closed = closed;
		this.inputFromAll = inputFromAll;
	}
}