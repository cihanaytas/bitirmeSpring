package com.ktu.bitirmeproje.business.dto.prod;


import com.ktu.bitirmeproje.utils.ColorType;
import com.ktu.bitirmeproje.utils.ProcessorModelType;
import com.ktu.bitirmeproje.utils.ProcessorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaptopDto {

	public LaptopDto(ProductDto product,int rAMsize, long hDDsize, ProcessorType processorType,
			ProcessorModelType processorModelType, ColorType color) {
 
		this.product = product;
		RAMsize = rAMsize;
		HDDsize = hDDsize;
		this.processorType = processorType;
		this.processorModelType = processorModelType;
		this.color = color;
	}

	private ProductDto product;
	
	private long laptopId;
	private int RAMsize;
	private long HDDsize;
	private ProcessorType processorType;
	private ProcessorModelType processorModelType;
	private ColorType color;
}
