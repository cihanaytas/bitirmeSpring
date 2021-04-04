package com.ktu.bitirmeproje.business.dto.prod;


import com.ktu.bitirmeproje.utils.ColorType;
import com.ktu.bitirmeproje.utils.ProcessorModelType;
import com.ktu.bitirmeproje.utils.ProcessorType;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LaptopDto {

	public LaptopDto(ProductDto product,String brand, String model, int rAMsize, long hDDsize, ProcessorType processorType,
			ProcessorModelType processorModelType, ColorType color) {
 
		this.product = product;
		this.brand = brand;
		this.model = model;
		RAMsize = rAMsize;
		HDDsize = hDDsize;
		this.processorType = processorType;
		this.processorModelType = processorModelType;
		this.color = color;
	}

	private ProductDto product;
	
	
	private String brand;
	private String model;
	private int RAMsize;
	private long HDDsize;
	private ProcessorType processorType;
	private ProcessorModelType processorModelType;
	private ColorType color;
}
