package com.ktu.bitirmeproje.business.impl;

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

@Service
@Transactional
public class TelevisionServiceImpl implements TelevisionService{

	@Autowired
	private UserAccountRepository uaRepository;
	
	@Autowired
	private TelevisionRepository tvRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void save(TelevisionDto tvDto) {
		Product product = new Product();
		Television tv = new Television();
		converToEntity(product, tv, tvDto);
		
		productRepository.save(product);
		tvRepository.save(tv);
		
	}
	
	
	
	public void converToEntity(Product product, Television tv, TelevisionDto tvDto) {
		product.setPrice(tvDto.getPrice());
		product.setDate(tvDto.getDate());
		Optional<UserAccount> store = uaRepository.findById(tvDto.getStoreNickName());
		product.setStore(store.get());
		product.setFeatures(tvDto.getFeatures());
		product.setCategory(tvDto.getCategoryType());
		product.setUnits(tvDto.getUnit());
		tv.setProduct(product);
		tv.setBrand(tvDto.getBrand());
		tv.setModel(tvDto.getModel());
		tv.setInc(tvDto.getInc());
		tv.setColor(tvDto.getColor());
	}

}
