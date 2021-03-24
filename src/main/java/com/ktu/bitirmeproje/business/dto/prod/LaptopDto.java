package com.ktu.bitirmeproje.business.dto.prod;

import java.util.Date;

import org.springframework.lang.Nullable;

import com.ktu.bitirmeproje.utils.CategoryType;
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

	public LaptopDto(long price, Date date, String storeNickName, String features,int unit,  CategoryType categoryType,
			String brand, String model, int rAMsize, long hDDsize, ProcessorType processorType,
			ProcessorModelType processorModelType, ColorType color) {
		this.price = price;
		this.date = date;
		StoreNickName = storeNickName;
		this.features = features;
		this.unit = unit;
		this.categoryType = categoryType;
		this.brand = brand;
		this.model = model;
		RAMsize = rAMsize;
		HDDsize = hDDsize;
		this.processorType = processorType;
		this.processorModelType = processorModelType;
		this.color = color;
	}
	@Nullable
	private long productID;
	private long price;
    private int unit;
	private java.util.Date date;
	private String StoreNickName;
	private String features;
	private CategoryType categoryType;
	
	@Nullable
	private long laptopId;
	private String brand;
	private String model;
	private int RAMsize;
	private long HDDsize;
	private ProcessorType processorType;
	private ProcessorModelType processorModelType;
	private ColorType color;
}
