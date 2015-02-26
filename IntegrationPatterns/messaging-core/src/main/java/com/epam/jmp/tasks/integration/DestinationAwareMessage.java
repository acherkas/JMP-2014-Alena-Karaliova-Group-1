package com.epam.jmp.tasks.integration;


class DestinationAwareMessage extends Message implements IDestinationAwareMessage{

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private String queueName;

	public DestinationAwareMessage(){
		super();
	}
	
	public DestinationAwareMessage(String queueName, IMessage message){
		super(message);
		this.queueName = queueName;
	}


	@Override
	public String getQueueName() {
		return queueName;
	}

	@Override
	public void setQueueName(String name) {
		queueName = name;
	}

}
