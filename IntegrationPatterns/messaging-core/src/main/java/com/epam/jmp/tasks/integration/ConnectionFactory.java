package com.epam.jmp.tasks.integration;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class ConnectionFactory {

	private String host;
	private Integer port;
	
	List<Connection> connections = new ArrayList<Connection>();
	
	public Connection createConnection() throws UnknownHostException, IOException{
		if(host == null || port==null){
			throw new IllegalStateException(new StringBuffer()
			.append("Host [")
			.append(host)
			.append("] and Port [")
			.append(port)
			.append("] must be set!").toString());
		}
		Connection c = new Connection(new Socket(host, port));
		connections.add(c);
		return c;
	}

	public void close() throws IOException{
		for(Connection c:connections){
			c.close();			
		}
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
}
