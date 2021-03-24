package com.ktu.bitirmeproje.business.dto.prod;

import java.util.Date;

import org.springframework.lang.Nullable;

import com.ktu.bitirmeproje.utils.CategoryType;
import com.ktu.bitirmeproje.utils.ColorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelevisionDto {

	@Nullable
	private long productID;
	private long price;
    private int unit;
	private java.util.Date date;
	private String StoreNickName;
	private String features;
	private CategoryType categoryType;
	
	@Nullable
	private long tvId;
	private String brand;	
	private String model;	
	private long inc;
	private ColorType color;
	
	
	public TelevisionDto(long price, Date date, String storeNickName, String features,int unit, CategoryType categoryType, String brand, String model,
			long inc, ColorType color) {
		this.price = price;
		this.date = date;
		StoreNickName = storeNickName;
		this.features = features;
		this.unit = unit;
		this.categoryType = categoryType;
		this.brand = brand;
		this.model = model;
		this.inc = inc;
		this.color = color;
	}
 

}
