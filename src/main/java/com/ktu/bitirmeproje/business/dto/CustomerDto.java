package com.ktu.bitirmeproje.business.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ktu.bitirmeproje.data.entity.Address;

public class CustomerDto {
	
	private String nickName;
	
	@NotNull
	@Size(min = 2 , max = 20 , message = "Incorret name")
	private String name;
	
	@NotNull
	@Size(min = 2 , max = 20 , message = "Incorret surname")
	private String surname;
	
	@NotBlank
	@Email
	private String e_mail;
	
	@NotNull
	@Size(min = 6, max = 20, message = "Your password should be at least 6 character")
	private String password;
	
	private Address address;
	
	
	
	
	public CustomerDto() {
	}

	public CustomerDto(String nickName, String name, String surname, String e_mail, String password, Address address) {
	 
		this.nickName = nickName;
		this.name = name;
		this.surname = surname;
		this.e_mail = e_mail;
		this.password = password;
		this.address = address;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}



}
