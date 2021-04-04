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

	@NotNull
	private long price;
	
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
