package com.epam.jmp.tasks.integration;

interface IDestinationAwareMessage extends IMessage{

	String getQueueName();
	void setQueueName(String name);
	
}
