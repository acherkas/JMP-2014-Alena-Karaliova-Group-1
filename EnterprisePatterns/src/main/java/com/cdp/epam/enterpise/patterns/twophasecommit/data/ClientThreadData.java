package com.cdp.epam.enterpise.patterns.twophasecommit.data;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;

import com.cdp.epam.enterpise.patterns.twophasecommit.Server;

public class ClientThreadData {
	public DataInputStream is;
	public String line;
	public String name;
	public PrintStream os;
	public Socket clientSocket;
	public String clientIdentity;
	public Server ser;

	public ClientThreadData(DataInputStream is, PrintStream os,
			Socket clientSocket) {
		this.is = is;
		this.os = os;
		this.clientSocket = clientSocket;
	}
}