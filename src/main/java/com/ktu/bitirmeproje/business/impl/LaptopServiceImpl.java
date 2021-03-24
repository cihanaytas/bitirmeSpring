package com.ktu.bitirmeproje.business.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktu.bitirmeproje.business.dto.prod.LaptopDto;
import com.ktu.bitirmeproje.business.service.LaptopService;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.Laptop;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.repository.LaptopRepository;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;

@Service
@Transactional
public class LaptopServiceImpl implements LaptopService{

	@Autowired
	private UserAccountRepository uaRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private LaptopRepository laptopRepository;
	
	@Override
	public void save(LaptopDto laptopDto) {
		Product product = new Product();
		Laptop laptop = new Laptop();
		convertToEntity(product, laptop, laptopDto);
		productRepository.save(product);		
		laptopRepository.save(laptop);
	}
	
	public void convertToEntity(Product product, Laptop laptop, LaptopDto laptopDto) {
		product.setPrice(laptopDto.getPrice());
		product.setDate(laptopDto.getDate());
		Optional<UserAccount> store = uaRepository.findById(laptopDto.getStoreNickName());
		product.setStore(store.get());
		product.setFeatures(laptopDto.getFeatures());
		product.setCategory(laptopDto.getCategoryType());
		product.setUnits(laptopDto.getUnit());
		
		laptop.setProduct(product);
		laptop.setBrand(laptopDto.getBrand());
		laptop.setModel(laptopDto.getModel());
		laptop.setRAMsize(laptopDto.getRAMsize());
		laptop.setHDDsize(laptopDto.getHDDsize());
		laptop.setProcessorType(laptopDto.getProcessorType());
		laptop.setProcessorModelType(laptopDto.getProcessorModelType());
		laptop.setColor(laptopDto.getColor());
		
	}

}
