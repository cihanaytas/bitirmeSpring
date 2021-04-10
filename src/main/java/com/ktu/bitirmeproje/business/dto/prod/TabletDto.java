package com.ktu.bitirmeproje.business.dto.prod;


import com.ktu.bitirmeproje.utils.ColorType;
import com.ktu.bitirmeproje.utils.PhoneType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TabletDto {

 
	public TabletDto(ProductDto product, int rAMsize, int camMP, long memory,
			ColorType color, PhoneType phoneType) {
		
		this.product = product;
		RAMsize = rAMsize;
		this.camMP = camMP;
		this.memory = memory;
		this.color = color;
		this.phoneType = phoneType;
	}
	
	
	private ProductDto product;
	

	private long tabletId;;
	private int RAMsize;
	private int camMP;
	private long memory;
	private ColorType color;
	private PhoneType phoneType;
 	
}
