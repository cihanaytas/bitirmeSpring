package com.ktu.bitirmeproje.business.service;

import java.util.List;

import com.ktu.bitirmeproje.business.dto.prod.ProductDto;


public interface ProductService {
	
	public List<ProductDto> getAllProducts();
	
	public List<ProductDto> getAllProductByNickname(String nickName);
	
	public ProductDto getProduct(long productId);
	
	public void pointProduct(long productId, double point);

	public void addProduct(ProductDto productDto);
	
    public List<ProductDto> getAllProducts(Integer pageNo, Integer pageSize, String sortBy);
    
    public List<ProductDto> getAllProductByCategory(Integer pageNo, Integer pageSize, String sortBy,String category);

}
