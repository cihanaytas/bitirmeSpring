package com.ktu.bitirmeproje.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
import com.ktu.bitirmeproje.business.service.ProductService;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;
import com.ktu.bitirmeproje.utils.CategoryType;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> list = (List<Product>) productRepository.getAllProduct();
		List<ProductDto> listDto = new ArrayList<>();		
		for(Product product : list) {
			ProductDto productDto = new ProductDto();
			convertToDto(product, productDto);
			listDto.add(productDto);
		}

		return listDto;
	}
	

	@Override
	public ProductDto getProduct(long productId) {
		Optional<Product> product = productRepository.findById(productId);
		ProductDto productDto = new ProductDto();
		convertToDto(product.get(), productDto);
		return productDto;
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
		
	}






}
