package com.springrest.springrest.modal;

public class MailInfo {

	private String receiver;
	private String subject;
	private String body;
	public MailInfo(String receiver, String subject, String body) {
		super();
		this.receiver = receiver;
		this.subject = subject;
		this.body = body;
	}
	public MailInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
