package com.ktu.bitirmeproje.business.dto.prod;


import com.ktu.bitirmeproje.utils.ColorType;
import com.ktu.bitirmeproje.utils.HeadPhoneType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HeadPhoneDto {

	public HeadPhoneDto(ProductDto product,
			HeadPhoneType type, ColorType color) {
		this.product = product;
		this.type = type;
		this.color = color;
	}

	private ProductDto product;
	
	private HeadPhoneType type;
	private ColorType color;
	
}
