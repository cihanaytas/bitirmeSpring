package com.ktu.bitirmeproje.utils;

import com.ktu.bitirmeproje.password.ValidPassword;


public class ReqBodyLogin {
	
	private String username;
	
	@ValidPassword
	private String password;
	
	
	public ReqBodyLogin() {
	}
	public ReqBodyLogin(String username, String password) {
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
