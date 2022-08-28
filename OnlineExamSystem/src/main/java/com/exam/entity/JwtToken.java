package com.exam.entity;

public class JwtToken {//whenever we want to give response in the form of token
	String token;

	public JwtToken() {
	
	}

	public JwtToken(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
