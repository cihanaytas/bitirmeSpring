package com.ktu.bitirmeproje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
import com.ktu.bitirmeproje.business.service.ProductService;

@RestController
public class CustomerController {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping("customer/products")
	public List<ProductDto> getAllProduct(){
		
		return productService.getAllProducts();
	}
}
