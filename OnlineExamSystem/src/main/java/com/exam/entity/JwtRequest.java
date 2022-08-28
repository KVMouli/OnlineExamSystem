package com.exam.entity;

public class JwtRequest {//whenever we want to accept the username and password we would call this entity
	private String username;
	private String password;
	
	public JwtRequest() {
	
	}

	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
