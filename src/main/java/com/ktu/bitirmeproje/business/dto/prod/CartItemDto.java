package com.ktu.bitirmeproje.business.dto.prod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

	private ProductDto product;
	private int adet;
}
