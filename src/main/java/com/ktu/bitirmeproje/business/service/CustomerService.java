package com.ktu.bitirmeproje.business.service;

import java.io.IOException;
import java.util.List;
import com.ktu.bitirmeproje.business.dto.prod.CartsProductsDto;
import com.ktu.bitirmeproje.business.dto.prod.ProductDto;


public interface CustomerService {
	
	public void sales(List<CartsProductsDto> cartList) throws IOException;

	public List<Long> getFavouriteList();

	public void addFavourite(Long productId);

	public void deleteFavourite(Long productId);

	public List<ProductDto> getFavouriteProductsList(Integer page, Integer pageSize);
 
}
