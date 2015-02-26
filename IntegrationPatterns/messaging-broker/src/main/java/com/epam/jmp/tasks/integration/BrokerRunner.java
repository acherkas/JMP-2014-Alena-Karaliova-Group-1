package com.epam.jmp.tasks.integration;

import java.io.IOException;


public class BrokerRunner 
{
    public static void main( String[] args ) throws IOException
    {
    	Integer port = Integer.parseInt(args[0]);
    	
    	Broker broker = new Broker(port);
    	broker.start();
    }
}
