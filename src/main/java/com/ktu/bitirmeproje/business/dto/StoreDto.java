package com.ktu.bitirmeproje.business.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ktu.bitirmeproje.data.entity.Address;
import com.ktu.bitirmeproje.data.entity.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreDto {
	
	private UserAccount store;
	private double point;

 
}
