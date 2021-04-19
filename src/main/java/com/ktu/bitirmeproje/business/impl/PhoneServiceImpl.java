package com.ktu.bitirmeproje.business.impl;

 

import javax.transaction.Transactional;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktu.bitirmeproje.business.dto.prod.PhoneDto;
import com.ktu.bitirmeproje.business.service.PhoneService;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.Phone;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.repository.PhoneRepository;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;
import com.ktu.bitirmeproje.utils.UserByAuth;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService{
	
	@Autowired
	private UserAccountRepository uaRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private ProductRepository productRepository;
	 
	@Autowired
	private UserByAuth uba;
	
	@Override
	public Product save(PhoneDto phoneDto) {

		UserAccount user = uba.getUserByAuth();
		
		phoneDto.getProduct().setStoreNickName(user.getNickName());
		Date date= new Date();
		
		phoneDto.getProduct().setDate(date);
		 
		Phone phone = new Phone();
		Product product = new Product();
			
		converToEntity(product, phone, phoneDto);
		//System.out.println(phone);
		
		phoneRepository.save(phone);
	 
		return productRepository.save(product);
	}

	
	public void converToEntity(Product product, Phone phone, PhoneDto phoneDto) {

		product.setPrice(phoneDto.getProduct().getPrice());
		product.setDate(phoneDto.getProduct().getDate());
		Optional<UserAccount> store = uaRepository.findById(phoneDto.getProduct().getStoreNickName());
		product.setStore(store.get());
		product.setFeatures(phoneDto.getProduct().getFeatures());
		product.setCategory(phoneDto.getProduct().getCategory());
		product.setUnits(phoneDto.getProduct().getUnits());
		product.setBrand(phoneDto.getProduct().getBrand());
		product.setModel(phoneDto.getProduct().getModel());
		 
		phone.setProduct(product);
		phone.setRAMsize(phoneDto.getRAMsize());
		phone.setCamMP(phoneDto.getCamMP());
		phone.setMemory(phoneDto.getMemory());
		phone.setColor(phoneDto.getColor());
		phone.setType(phoneDto.getType());
	}

}
