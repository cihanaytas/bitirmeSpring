package com.ktu.bitirmeproje.business.dto.prod;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.ktu.bitirmeproje.utils.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	public ProductDto(@NotNull long price, @NotNull String brand, @NotNull String model, @NotNull Date date,
			@NotNull String storeNickName, @NotNull CategoryType category, @NotNull String features,
			@NotNull int units) {
		this.price = price;
		this.brand = brand;
		this.model = model;
		this.date = date;
		StoreNickName = storeNickName;
		this.category = category;
		this.features = features;
		this.units = units;
	}

	private long id;

	@NotNull
	private long price;
	
	@NotNull
	private String brand;

	@NotNull
	private String model;
	
	private String productName;
	
	@NotNull
	private Date date;
	
	@NotNull
	private String StoreNickName;
	
	@NotNull
	private CategoryType category;
	
	@NotNull
	private String features;
	
	@NotNull
	private int units;
	
}
