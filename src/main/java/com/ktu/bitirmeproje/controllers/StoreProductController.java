package com.ktu.bitirmeproje.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
import com.ktu.bitirmeproje.business.service.ProductService;
import com.ktu.bitirmeproje.data.entity.prod.Product;


@RestController
@RequestMapping("store")
public class StoreProductController {


	@Autowired
	private ProductService productService;
	
		
	
	@PostMapping("/addproduct")
	public String addProduct(@Valid @RequestBody ProductDto productDto) {
		productService.addProduct(productDto);
		return "a";
	}
	
	
}
