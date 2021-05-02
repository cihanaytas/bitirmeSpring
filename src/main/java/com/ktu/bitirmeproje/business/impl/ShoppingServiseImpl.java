package com.ktu.bitirmeproje.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktu.bitirmeproje.business.dto.prod.ShoppingDto;
import com.ktu.bitirmeproje.business.service.ShoppingService;
import com.ktu.bitirmeproje.data.entity.CustomerDetails;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.Shopping;
import com.ktu.bitirmeproje.data.repository.CustomerRepository;
import com.ktu.bitirmeproje.data.repository.ShoppingRepository;
import com.ktu.bitirmeproje.utils.UserByAuth;

@Service
public class ShoppingServiseImpl implements ShoppingService{
	
	@Autowired
	private UserByAuth userByAuth;
	
	@Autowired
	private ShoppingRepository shopRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<ShoppingDto> getShoppingList() {
		UserAccount user = userByAuth.getUserByAuth();
		CustomerDetails customer = customerRepository.findByCustomer(user);
		
		List<Shopping> shopList = shopRepository.findByCustomerOrderByDateDesc(customer);
		List<ShoppingDto> shopDtoList = new ArrayList<ShoppingDto>();
		
		for(Shopping shop : shopList) {
			ShoppingDto shopDto = new ShoppingDto();
			convertToDto(shop,shopDto);
			shopDtoList.add(shopDto);
		}
		
		return shopDtoList;
	}
	
	
	
	
	public void convertToEntity(Shopping shop, ShoppingDto shopDto) {
		
	}
	
	
	
	public void convertToDto(Shopping shop, ShoppingDto shopDto) {
		shopDto.setId(shop.getID());
		shopDto.setCustomerNickName(shop.getCustomer().getCustomer().getNickName());
		shopDto.setDate(shop.getDate());
		shopDto.setTotalPrice(shop.getTotalPrice());
	
	}

}
