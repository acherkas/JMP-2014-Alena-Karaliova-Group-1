package com.epam.jmp.tasks.integration;

import java.io.IOException;

public class InboundChannelAdapter {

	private String queueName;
	
	private ConnectionFactory connectionFactory;
	
	private Connection connection;
	
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public void setDefaulQueueName(String queueName){
		this.queueName = queueName;
	}
	
	public void send(IMessageCreator messageCreator){
		send(queueName, messageCreator);
	}
	
	public void send(String QueueName, IMessageCreator messageCreator){
		if(connectionFactory == null){
			throw new IllegalStateException("ConnectionFactory must be set!");
		}
		
		if(connection == null){
			try {
				connection = connectionFactory.createConnection();
			} catch (IOException e) {
				throw new RuntimeException("Error creating connection.", e);
			}
		}
		
		try {
			connection.send(new DestinationAwareMessage(queueName, messageCreator.create()));

		} catch (IOException e) {
			try {
				connection.close();
			} catch (IOException e1) {
				
			}
			connection = null;
			throw new RuntimeException("Error while sending message.", e);
		}
	}
}
