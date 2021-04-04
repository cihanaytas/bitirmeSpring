package com.ktu.bitirmeproje.business.impl;

import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktu.bitirmeproje.business.dto.prod.TelevisionDto;
import com.ktu.bitirmeproje.business.service.TelevisionService;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.entity.prod.Television;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import com.ktu.bitirmeproje.data.repository.TelevisionRepository;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;
import com.ktu.bitirmeproje.utils.UserByAuth;

@Service
@Transactional
public class TelevisionServiceImpl implements TelevisionService{

	@Autowired
	private UserAccountRepository uaRepository;
	
	@Autowired
	private TelevisionRepository tvRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserByAuth uba;
	
	@Override
	public void save(TelevisionDto tvDto) {
		
		UserAccount user = uba.getUserByAuth();
		
		tvDto.getProduct().setStoreNickName(user.getNickName());
		Date date= new Date();
		
		tvDto.getProduct().setDate(date);
		
		Product product = new Product();
		Television tv = new Television();
		converToEntity(product, tv, tvDto);
		
		productRepository.save(product);
		tvRepository.save(tv);
		
	}
	
	
	
	public void converToEntity(Product product, Television tv, TelevisionDto tvDto) {
		product.setPrice(tvDto.getProduct().getPrice());
		product.setDate(tvDto.getProduct().getDate());
		Optional<UserAccount> store = uaRepository.findById(tvDto.getProduct().getStoreNickName());
		product.setStore(store.get());
		product.setFeatures(tvDto.getProduct().getFeatures());
		product.setCategory(tvDto.getProduct().getCategory());
		product.setUnits(tvDto.getProduct().getUnits());
		
		tv.setProduct(product);
		tv.setBrand(tvDto.getBrand());
		tv.setModel(tvDto.getModel());
		tv.setInc(tvDto.getInc());
		tv.setColor(tvDto.getColor());
	}

}
