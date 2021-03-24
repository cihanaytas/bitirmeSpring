package com.ktu.bitirmeproje.business.impl;

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

@Service
@Transactional
public class TabletServiceImpl implements TabletService{

	@Autowired
	private UserAccountRepository uaRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private TabletRepository tabletRepository;
	
	@Override
	public void save(TabletDto tabletDto) {
		Product product = new Product();
		Tablet tablet = new Tablet();
		converToEntity(product, tablet, tabletDto);
		productRepository.save(product);
		tabletRepository.save(tablet);
		
	}
	
	
	public void converToEntity(Product product, Tablet tablet, TabletDto tabletDto) {
		product.setPrice(tabletDto.getPrice());
		product.setDate(tabletDto.getDate());
		Optional<UserAccount> store = uaRepository.findById(tabletDto.getStoreNickName());
		product.setStore(store.get());
		product.setFeatures(tabletDto.getFeatures());
		product.setCategory(tabletDto.getCategoryType());
		product.setUnits(tabletDto.getUnit());
		
		tablet.setProduct(product);
		tablet.setPhonetype(tabletDto.getPhonetype());
		tablet.setBrand(tabletDto.getBrand());
		tablet.setModel(tabletDto.getModel());
		tablet.setRAMsize(tabletDto.getRAMsize());
		tablet.setCamMP(tabletDto.getCamMP());
		tablet.setMemory(tabletDto.getMemory());
		tablet.setColor(tabletDto.getColor());
	}

}
