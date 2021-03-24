package com.ktu.bitirmeproje.business.dto.prod;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class PhoneDto {
	
	public PhoneDto(long price, Date date, String storeNickName, String features,int unit, CategoryType categoryType, String brand, String model,
			int rAMsize, int camMP, long memory, ColorType color, PhoneType type) {
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
		this.type = type;
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
	private long phoneId;
	private String brand;	
	private String model;	
	private int RAMsize;	
	private int camMP;
	private long memory;
	private ColorType color;
	private PhoneType type;

}
