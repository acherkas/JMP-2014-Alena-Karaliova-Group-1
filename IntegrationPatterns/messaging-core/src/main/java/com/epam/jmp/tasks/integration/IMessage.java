package com.epam.jmp.tasks.integration;

import java.io.Serializable;
import java.util.List;

public interface IMessage extends Serializable{

	String getHeader(String name);

	void setHeader(String name, String value);
	
	List<IHeader> getAllHeaders();
	
	String getBody();

	void setBody(String body);
	
}
