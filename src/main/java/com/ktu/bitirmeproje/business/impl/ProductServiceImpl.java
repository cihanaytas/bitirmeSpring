package com.ktu.bitirmeproje.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
import com.ktu.bitirmeproje.business.service.ProductService;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.PointsOfProduct;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;
import com.ktu.bitirmeproje.utils.CategoryType;
import com.ktu.bitirmeproje.utils.UserByAuth;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserAccountRepository uaRep;
	
	@Autowired
	private UserByAuth uba;
	

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> list = (List<Product>) productRepository.getAllProduct();
		List<ProductDto> listDto = new ArrayList<>();	
		
		for(Product product : list) {
			ProductDto productDto = new ProductDto();
			ModelMapper modelMapper = new ModelMapper();
	 		productDto = modelMapper.map(product, ProductDto.class);
			//convertToDto(product, productDto);
			listDto.add(productDto);
		}

		return listDto;
	}	
	
	@Override
	public List<ProductDto> getAllProductByNickname(String nickName) {
		Optional<UserAccount> store = uaRep.findById(nickName);
		
		List<Product> list = (List<Product>) productRepository.getAllProductByNickname(store.get());
		List<ProductDto> listDto = new ArrayList<>();	
		
		for(Product product : list) {
			ProductDto productDto = new ProductDto();
			ModelMapper modelMapper = new ModelMapper();
	 		productDto = modelMapper.map(product, ProductDto.class);
			//convertToDto(product, productDto);
			listDto.add(productDto);
		}

		return listDto;
	}
	

	@Override
	public ProductDto getProduct(long productId) {
		Optional<Product> product = productRepository.findById(productId);
		ProductDto productDto = new ProductDto();
		ModelMapper modelMapper = new ModelMapper();
 		productDto = modelMapper.map(product.get(), ProductDto.class);
		//convertToDto(product.get(), productDto);
		return productDto;
	}
	
	
	@Override
	public void pointProduct(long productId, double point) {
		Optional<Product> p = productRepository.findById(productId);
		Product product = p.get();
		UserAccount user = uba.getUserByAuth();
		PointsOfProduct pop = new PointsOfProduct();
		pop.setUser(user);
		pop.setProduct(product);
		pop.setPoint(point);
		product.getPoints().add(pop);

		productRepository.save(product);		
	}
	
	
	

	
	private void convertToDto(Product product, ProductDto productDto) {
		productDto.setId(product.getProductID());
		productDto.setPrice(product.getPrice());
		productDto.setBrand(product.getBrand());
		productDto.setModel(product.getModel());
		productDto.setDate(product.getDate());
		productDto.setStoreNickName(product.getStore().getNickName());
		productDto.setCategory(product.getCategory());
		productDto.setFeatures(product.getFeatures());
		productDto.setUnits(product.getUnits());
 		//productDto.setPoints(product.getPoints());
 		
 		}












}
