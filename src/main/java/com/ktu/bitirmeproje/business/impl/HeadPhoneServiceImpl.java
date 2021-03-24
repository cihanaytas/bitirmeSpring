package com.ktu.bitirmeproje.business.impl;

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

@Service
@Transactional
public class HeadPhoneServiceImpl implements HeadPhoneService{

	@Autowired
	private UserAccountRepository uaRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private HeadPhoneRepository headPhoneRepository;
	
	@Override
	public void save(HeadPhoneDto headPhoneDto) {
		Product product = new Product();
		HeadPhone headPhone = new HeadPhone();
		convertToEntity(product, headPhone, headPhoneDto);
		productRepository.save(product);
		headPhoneRepository.save(headPhone);
		
	}
	
	public void convertToEntity(Product product, HeadPhone headPhone, HeadPhoneDto hpDto) {
		product.setPrice(hpDto.getPrice());
		product.setDate(hpDto.getDate());
		Optional<UserAccount> store = uaRepository.findById(hpDto.getStoreNickName());
		product.setStore(store.get());
		product.setFeatures(hpDto.getFeatures());
		product.setCategory(hpDto.getCategoryType());
		product.setUnits(hpDto.getUnit());
		
		headPhone.setProduct(product);
		headPhone.setColor(hpDto.getColor());
		headPhone.setType(hpDto.getType());
		
	}

}
