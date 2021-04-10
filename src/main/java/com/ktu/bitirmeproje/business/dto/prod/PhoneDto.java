package com.ktu.bitirmeproje.business.dto.prod;


import com.ktu.bitirmeproje.utils.ColorType;
import com.ktu.bitirmeproje.utils.PhoneType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {
	
	public PhoneDto(ProductDto product,
			int rAMsize, int camMP, long memory, ColorType color, PhoneType type) {
		this.product = product;
		RAMsize = rAMsize;
		this.camMP = camMP;
		this.memory = memory;
		this.color = color;
		this.type = type;
	}

	private ProductDto product;

	private long phoneId;	
	private int RAMsize;	
	private int camMP;
	private long memory;
	private ColorType color;
	private PhoneType type;

}
