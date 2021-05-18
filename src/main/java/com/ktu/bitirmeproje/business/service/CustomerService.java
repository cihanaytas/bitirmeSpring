package com.ktu.bitirmeproje.business.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ktu.bitirmeproje.business.dto.CustomerDto;
import com.ktu.bitirmeproje.business.dto.FavouriteProductsDto;
import com.ktu.bitirmeproje.business.dto.prod.CartsProductsDto;
import com.ktu.bitirmeproje.exception.ResourceNotFoundException;
import com.ktu.bitirmeproje.utils.ReqBodyLogin;

public interface CustomerService {
	
	public void sales(List<CartsProductsDto> cartList) throws IOException;

	public List<Long> getFavouriteList();

	public void addFavourite(Long productId);

	public void deleteFavourite(Long productId);
 
}
