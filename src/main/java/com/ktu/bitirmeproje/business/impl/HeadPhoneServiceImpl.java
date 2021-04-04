package com.ktu.bitirmeproje.business.impl;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktu.bitirmeproje.business.dto.prod.HeadPhoneDto;
import com.ktu.bitirmeproje.business.service.HeadPhoneService;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.HeadPhone;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.repository.HeadPhoneRepository;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;
import com.ktu.bitirmeproje.utils.UserByAuth;

@Service
@Transactional
public class HeadPhoneServiceImpl implements HeadPhoneService{

	@Autowired
	private UserAccountRepository uaRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private HeadPhoneRepository headPhoneRepository;
	
	@Autowired
	private UserByAuth uba;
	
	@Override
	public void save(HeadPhoneDto headPhoneDto) {
		

		UserAccount user = uba.getUserByAuth();
		
		headPhoneDto.getProduct().setStoreNickName(user.getNickName());
		Date date= new Date();
		
		headPhoneDto.getProduct().setDate(date);
		
				
		Product product = new Product();
		HeadPhone headPhone = new HeadPhone();
		convertToEntity(product, headPhone, headPhoneDto);
		productRepository.save(product);
		headPhoneRepository.save(headPhone);
		
	}
	
	public void convertToEntity(Product product, HeadPhone headPhone, HeadPhoneDto hpDto) {
		product.setPrice(hpDto.getProduct().getPrice());
		product.setDate(hpDto.getProduct().getDate());
		Optional<UserAccount> store = uaRepository.findById(hpDto.getProduct().getStoreNickName());
		product.setStore(store.get());
		product.setFeatures(hpDto.getProduct().getFeatures());
		product.setCategory(hpDto.getProduct().getCategory());
		product.setUnits(hpDto.getProduct().getUnits());
		
		headPhone.setProduct(product);
		headPhone.setColor(hpDto.getColor());
		headPhone.setType(hpDto.getType());
		
	}

}
