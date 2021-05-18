package com.ktu.bitirmeproje.business.dto;

import com.ktu.bitirmeproje.data.entity.CustomerDetails;
import com.ktu.bitirmeproje.data.entity.prod.FavouriteProducts;
import com.ktu.bitirmeproje.data.entity.prod.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteProductsDto {

	private long ID;
	private String customerNickName;
	private long productId;
	
}
