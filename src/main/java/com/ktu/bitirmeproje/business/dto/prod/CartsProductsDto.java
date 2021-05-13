package com.ktu.bitirmeproje.business.dto.prod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartsProductsDto {
	
	private long ID;
	private long shoppingId;
	private long productId;
	private double price;
	private int quantity;
	private String category;

}
