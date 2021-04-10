package com.ktu.bitirmeproje.business.dto.prod;


import com.ktu.bitirmeproje.utils.ColorType;
import com.ktu.bitirmeproje.utils.HeadPhoneType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeadPhoneDto {

	public HeadPhoneDto(ProductDto product,
			HeadPhoneType type, ColorType color) {
		this.product = product;
		this.type = type;
		this.color = color;
	}

	private long headhPhoneId;
	private ProductDto product;
	
	private HeadPhoneType type;
	private ColorType color;
	
}
