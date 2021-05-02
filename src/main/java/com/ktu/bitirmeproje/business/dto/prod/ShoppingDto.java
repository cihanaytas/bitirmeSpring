package com.ktu.bitirmeproje.business.dto.prod;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingDto {

	private long id;
	private String customerNickName;
	private Date date;
	private double totalPrice;
}
