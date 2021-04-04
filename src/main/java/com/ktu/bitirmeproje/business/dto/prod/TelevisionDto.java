package com.ktu.bitirmeproje.business.dto.prod;


import com.ktu.bitirmeproje.utils.ColorType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TelevisionDto {

	private ProductDto product;
	
 
	private String brand;	
	private String model;	
	private long inc;
	private ColorType color;
	
	
	public TelevisionDto(ProductDto product, String brand, String model,
			long inc, ColorType color) {
		this.product = product;
		this.brand = brand;
		this.model = model;
		this.inc = inc;
		this.color = color;
	}
 

}
