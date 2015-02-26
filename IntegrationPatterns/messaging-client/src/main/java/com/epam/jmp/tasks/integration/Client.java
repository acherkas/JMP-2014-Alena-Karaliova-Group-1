package com.epam.jmp.tasks.integration;

import com.epam.jmp.tasks.integration.ConnectionFactory;

public abstract class Client {

	public static final String QUEUE_NAME =
			"SampleQueue";
	
	protected ConnectionFactory connectionFactory;

	public Client(ConnectionFactory connectionFactory) {
		super();
		this.connectionFactory = connectionFactory;
	}

	public abstract void start();
	
}
