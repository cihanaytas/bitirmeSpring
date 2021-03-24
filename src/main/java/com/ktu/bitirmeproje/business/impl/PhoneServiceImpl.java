package com.ktu.bitirmeproje.business.impl;

 

import javax.transaction.Transactional;
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

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService{
	
	@Autowired
	private UserAccountRepository uaRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	 @Autowired
	 private ProductRepository productRepository;
	
	@Override
	public Phone save(PhoneDto phoneDto) {
		
		 
		Phone phone = new Phone();
		Product product = new Product();
			
		converToEntity(product, phone, phoneDto);
		//System.out.println(phone);
		productRepository.save(product);
		phoneRepository.save(phone);
	 
		return null;
	}

	
	public void converToEntity(Product product, Phone phone, PhoneDto phoneDto) {

		product.setPrice(phoneDto.getPrice());
		product.setDate(phoneDto.getDate());
		Optional<UserAccount> store = uaRepository.findById(phoneDto.getStoreNickName());
		product.setStore(store.get());
		product.setFeatures(phoneDto.getFeatures());
		product.setCategory(phoneDto.getCategoryType());
		product.setUnits(phoneDto.getUnit());
		 
		phone.setProduct(product);
		phone.setBrand(phoneDto.getBrand());
		phone.setModel(phoneDto.getModel());
		phone.setRAMsize(phoneDto.getRAMsize());
		phone.setCamMP(phoneDto.getCamMP());
		phone.setMemory(phoneDto.getMemory());
		phone.setColor(phoneDto.getColor());
		phone.setType(phoneDto.getType());
	}

}
