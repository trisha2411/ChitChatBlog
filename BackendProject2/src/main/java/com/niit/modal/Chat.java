package com.niit.modal;

public class Chat
{
	private String message;
	private String to;
	private String from;
	
	@Override
	public String toString() {
		return "Chat [message=" + message + ", to=" + to + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	

}
