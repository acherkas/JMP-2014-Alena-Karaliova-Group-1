package com.epam.jmp.tasks.integration;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {

	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	public Connection(Socket socket) throws UnknownHostException, IOException{
		this.socket = socket;
	} 
	
	public void close() throws IOException{
		in.close();
		out.close();
		socket.close();
	}
	
	public void send(IDestinationAwareMessage message) throws IOException{

		if(out == null){
			out = new ObjectOutputStream(this.socket.getOutputStream());
		}
		
		out.writeObject(message);
		out.flush();
	}
	
	public IDestinationAwareMessage recive(String queueName) throws IOException{
		
		if(out == null){
			out = new ObjectOutputStream(this.socket.getOutputStream());
		}
		
		out.writeObject(queueName);
		out.flush();
		
		if(in==null){
			in = new ObjectInputStream(this.socket.getInputStream());
		}
		
		try {
			Object o = in.readObject();
			if(o instanceof DestinationAwareMessage){
				return (DestinationAwareMessage) o;
			} else {
				throw new IllegalStateException("Recived unexpected message.[" + o + "]");
			}
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Recived unexpected message type.", e);
		}
		
	}
	
}
