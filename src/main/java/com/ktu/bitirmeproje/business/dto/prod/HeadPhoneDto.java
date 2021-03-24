package com.ktu.bitirmeproje.business.dto.prod;

import java.util.Date;

import org.springframework.lang.Nullable;

import com.ktu.bitirmeproje.utils.CategoryType;
import com.ktu.bitirmeproje.utils.ColorType;
import com.ktu.bitirmeproje.utils.HeadPhoneType;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeadPhoneDto {

	public HeadPhoneDto(long price, Date date, String storeNickName, String features,int unit, CategoryType categoryType,
			HeadPhoneType type, ColorType color) {
		this.price = price;
		this.date = date;
		StoreNickName = storeNickName;
		this.features = features;
		this.unit = unit;
		this.categoryType = categoryType;
		this.type = type;
		this.color = color;
	}
	@Nullable
	private long productID;
	private long price;
	private java.util.Date date;
	private String StoreNickName;
	private String features;
    private int unit;
	private CategoryType categoryType;
	
	private long headPhoneId;
	private HeadPhoneType type;
	private ColorType color;
	
}
