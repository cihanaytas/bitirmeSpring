package com.ktu.bitirmeproje.business.service;

import java.util.List;

import com.ktu.bitirmeproje.business.dto.prod.CartItemDto;
import com.ktu.bitirmeproje.business.dto.prod.ShoppingDto;

public interface ShoppingService {

	public List<ShoppingDto> getShoppingList();
	
	public List<CartItemDto> getCartItemList(long shoppingId);
}