package com.ktu.bitirmeproje.business.dto.prod;


import com.ktu.bitirmeproje.utils.ColorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelevisionDto {

	private ProductDto product;
	
	private long televisinId;	
	private long inc;
	private ColorType color;
	
	
	public TelevisionDto(ProductDto product,
			long inc, ColorType color) {
		this.product = product;
		this.inc = inc;
		this.color = color;
	}
 

}
