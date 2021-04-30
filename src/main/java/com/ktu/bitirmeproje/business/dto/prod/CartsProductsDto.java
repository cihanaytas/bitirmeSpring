package com.ktu.bitirmeproje.business.dto.prod;

import com.ktu.bitirmeproje.data.entity.prod.CartsProducts;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.entity.prod.Shopping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartsProductsDto {
	
	private long ID;
	private long shoppingId;
	private long productId;
	private int quantity;

}
