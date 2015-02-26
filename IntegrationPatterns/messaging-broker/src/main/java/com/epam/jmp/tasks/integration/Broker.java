package com.epam.jmp.tasks.integration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.integration.IDestinationAwareMessage;

public class Broker {

	private static final Logger LOGGER = Logger.getLogger(Broker.class);
	
	private Integer port;
	
	private ConcurrentMap<String, BlockingQueue<IDestinationAwareMessage>> messages =
			new ConcurrentHashMap<>();
	
	private boolean isRunning = false;
	
	public Broker(Integer port){
		this.port = port;
	}
	
	public void start() throws IOException{
		if(port == null){
			throw new IllegalStateException("Port has not been set!");
		}
		isRunning = true;
		
		ServerSocket serverSocket = null;
		
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		
		try {
			serverSocket = new ServerSocket(port);
			while(isRunning){
				Socket socket = serverSocket.accept();
                executorService.submit(new SocketHandler(socket));
			}
			
		} finally {
			try {
				serverSocket.close();
			} catch (Exception e2) {
			}
			executorService.shutdownNow();
		}
		
		
	}
	
	public void stop(){
		isRunning = false;
	}
	
	private class SocketHandler implements Callable<Object>{
		
		Socket socket;
		
		public SocketHandler(Socket socket){
			this.socket = socket;
		}

		@Override
		public Object call() throws Exception {
			ObjectInputStream input =
					new ObjectInputStream(socket.getInputStream());
			
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			
			while(!socket.isClosed()){
				try {
					Object ret = input.readObject();
					
					if(ret instanceof String){
						handleReceive((String)ret, output);
					} else {
						if(ret instanceof IDestinationAwareMessage){
							handleSend((IDestinationAwareMessage) ret);
						} else {
							LOGGER.error("Recived unexpected message type [" + ret.getClass() + "]");
							throw new IllegalArgumentException("Recived unexpected message type [" + ret.getClass() + "]");
						}
					} 
					
				} catch (Exception e) {
					LOGGER.error("Error while handling socket", e);
					throw e;
				}	
			}

			return null;
		}
		
		private void handleReceive(String queueName, ObjectOutputStream out){
			BlockingQueue<IDestinationAwareMessage> queue = messages.get(queueName);
			if(queue == null){
				queue = new LinkedBlockingQueue<>();
				messages.put(queueName, queue);
			}
			try {
				IDestinationAwareMessage message = queue.take();
				try {

					out.writeObject(message);
					out.flush();
				} catch (IOException e) {
					LOGGER.error("Error while writing response to socket.", e);
				}
			} catch (InterruptedException e) {
				LOGGER.error("Error while dequeuing message.", e);
			}
			
		}
		
		private void handleSend(IDestinationAwareMessage message){
			
			BlockingQueue<IDestinationAwareMessage> queue = messages.get(message.getQueueName());
			
			if(queue == null){
				queue = new LinkedBlockingQueue<IDestinationAwareMessage>();
				messages.put(message.getQueueName(), queue);
			}
			
			try {
				queue.put(message);
			} catch (InterruptedException e) {
				LOGGER.error("Error while enqueuing message.", e);
			}
		}
		
	}
	
}
