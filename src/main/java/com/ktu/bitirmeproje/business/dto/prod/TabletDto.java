package com.ktu.bitirmeproje.business.dto.prod;

import java.util.Date;

import org.springframework.lang.Nullable;

import com.ktu.bitirmeproje.utils.CategoryType;
import com.ktu.bitirmeproje.utils.ColorType;
import com.ktu.bitirmeproje.utils.PhoneType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TabletDto {

 
	public TabletDto(PhoneType type, long price, Date date, String storeNickName, String features,int unit, 
			CategoryType categoryType, String brand, String model, int rAMsize, int camMP, long memory,
			ColorType color) {
			this.phonetype = type;
		this.price = price;
		this.date = date;
		StoreNickName = storeNickName;
		this.features = features;
		this.unit = unit;
		this.categoryType = categoryType;
		this.brand = brand;
		this.model = model;
		RAMsize = rAMsize;
		this.camMP = camMP;
		this.memory = memory;
		this.color = color;
	}
	@Nullable
	private long productID;
	private PhoneType phonetype;
	private long price;
	private java.util.Date date;
	private String StoreNickName;
	private String features;
	private CategoryType categoryType;
	
	@Nullable
	private long tabletId;
	private String brand;
	private String model;
    private int unit;
	private int RAMsize;
	private int camMP;
	private long memory;
	private ColorType color;
 	
}
