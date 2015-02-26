package com.epam.jmp.tasks.integration;

import com.epam.jmp.tasks.integration.ConnectionFactory;

public class ClientsRunner {

	public static void main(String[] args) {
		Integer port = Integer.parseInt(args[0]);
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(port);
		
		Producer producer = new Producer(connectionFactory);
		new Thread(new ClientRunner(producer)).start();
		
		Consumer consumer1 = new Consumer(connectionFactory);
		new Thread(new ClientRunner(consumer1)).start();
		
		Consumer consumer2 = new Consumer(connectionFactory);
		new Thread(new ClientRunner(consumer2)).start();
	}
	
	private static class ClientRunner implements Runnable {

		Client client;
	
		public ClientRunner(Client client) {
			super();
			this.client = client;
		}

		public void run() {
			client.start();
		}
		
	}

}
