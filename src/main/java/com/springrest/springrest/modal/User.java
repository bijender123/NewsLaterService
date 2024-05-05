package com.springrest.springrest.modal;

public class User {

	private long user_id;
	private String user_email;
	public User(long user_id, String user_email) {
		super();
		this.user_id = user_id;
		this.user_email = user_email;
	}
	public User() {
		super();
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
}
