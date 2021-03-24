package com.ktu.bitirmeproje.business.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.ktu.bitirmeproje.data.entity.Address;
import com.ktu.bitirmeproje.utils.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {
	
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
	
	@Nullable
	private Address address;
	private UserRole role;
}
