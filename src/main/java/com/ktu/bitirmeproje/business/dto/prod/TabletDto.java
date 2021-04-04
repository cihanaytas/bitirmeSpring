package com.ktu.bitirmeproje.business.dto.prod;


import com.ktu.bitirmeproje.utils.ColorType;
import com.ktu.bitirmeproje.utils.PhoneType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TabletDto {

 
	public TabletDto(ProductDto product, String brand, String model, int rAMsize, int camMP, long memory,
			ColorType color, PhoneType phoneType) {
		
		this.product = product;
		this.brand = brand;
		this.model = model;
		RAMsize = rAMsize;
		this.camMP = camMP;
		this.memory = memory;
		this.color = color;
		this.phoneType = phoneType;
	}
	
	
	private ProductDto product;
	


	private String brand;
	private String model;
	private int RAMsize;
	private int camMP;
	private long memory;
	private ColorType color;
	private PhoneType phoneType;
 	
}
