package com.epam.jmp.tasks.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Message implements IMessage{

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, String> headers = new HashMap<>();
	
	private String body;

	public Message(){
		super();
	}
	
	public Message(IMessage message){
		super();
		for(IHeader header:message.getAllHeaders()){
			headers.put(header.getName(), header.getValue());
		}
		body = message.getBody();
	}
	
	public String getHeader(String name){
		return headers.get(name);
	}
	
	public void setHeader(String name, String value){
		headers.put(name, value);
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public List<IHeader> getAllHeaders() {
		List<IHeader > hh = new ArrayList<IHeader>();
		for(Entry<String, String> e:headers.entrySet()){
			hh.add(new Header(e.getKey(), e.getValue()));
		}
		return hh;
	}
	
	private class Header implements IHeader {

		private String name;
		private String value;
		
		public Header(String name, String value){
			this.name = name;
			this.value = value;
		}
		
		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getValue() {
			return value;
		}
		
	}
	
}
