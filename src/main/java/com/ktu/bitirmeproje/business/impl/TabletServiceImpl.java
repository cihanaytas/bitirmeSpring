package com.ktu.bitirmeproje.business.impl;

import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktu.bitirmeproje.business.dto.prod.TabletDto;
import com.ktu.bitirmeproje.business.service.TabletService;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.entity.prod.Tablet;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import com.ktu.bitirmeproje.data.repository.TabletRepository;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;
import com.ktu.bitirmeproje.utils.UserByAuth;

@Service
@Transactional
public class TabletServiceImpl implements TabletService{

	@Autowired
	private UserAccountRepository uaRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private TabletRepository tabletRepository;
	
	@Autowired
	private UserByAuth uba;
	
	@Override
	public void save(TabletDto tabletDto) {

		UserAccount user = uba.getUserByAuth();
		
		tabletDto.getProduct().setStoreNickName(user.getNickName());
		Date date= new Date();
		
		tabletDto.getProduct().setDate(date);
		
		Product product = new Product();
		Tablet tablet = new Tablet();
		converToEntity(product, tablet, tabletDto);
		productRepository.save(product);
		tabletRepository.save(tablet);
		
	}
	
	
	public void converToEntity(Product product, Tablet tablet, TabletDto tabletDto) {
		product.setPrice(tabletDto.getProduct().getPrice());
		product.setDate(tabletDto.getProduct().getDate());
		Optional<UserAccount> store = uaRepository.findById(tabletDto.getProduct().getStoreNickName());
		product.setStore(store.get());
		product.setFeatures(tabletDto.getProduct().getFeatures());
		product.setCategory(tabletDto.getProduct().getCategory());
		product.setUnits(tabletDto.getProduct().getUnits());
		product.setBrand(tabletDto.getProduct().getBrand());
		product.setModel(tabletDto.getProduct().getModel());
		
		tablet.setProduct(product);
		tablet.setPhonetype(tabletDto.getPhoneType());
		tablet.setRAMsize(tabletDto.getRAMsize());
		tablet.setCamMP(tabletDto.getCamMP());
		tablet.setMemory(tabletDto.getMemory());
		tablet.setColor(tabletDto.getColor());
	}

}
