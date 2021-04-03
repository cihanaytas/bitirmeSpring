package com.ktu.bitirmeproje.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktu.bitirmeproje.business.dto.prod.LaptopDto;
import com.ktu.bitirmeproje.business.service.LaptopService;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;
import com.ktu.bitirmeproje.utils.CategoryType;
import com.ktu.bitirmeproje.utils.ColorType;
import com.ktu.bitirmeproje.utils.ProcessorModelType;
import com.ktu.bitirmeproje.utils.ProcessorType;

@RestController
@RequestMapping("store")
public class StoreController {

	@Autowired
	private ProductRepository prep;
	
	@Autowired
	private UserAccountRepository urep;
	
	@Autowired
	private LaptopService laptopService;
	
	
	@PostMapping("/addproduct")
	public String addProduct(@RequestBody Product product){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<UserAccount> user = urep.findById(authentication.getName());
		
		product.setStore(user.get());
		Date date = new Date();
		product.setDate(date);
		if(prep.save(product) != null) {
			return "ok";
		}
		else {
			return "";
		}
		
	}
	
	
	
	@PostMapping("/addlaptop")
	public String laptop(@RequestBody LaptopDto laptop) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<UserAccount> user = urep.findById(authentication.getName());
		
		laptop.setStoreNickName(user.get().getNickName());
		Date date= new Date();
		laptop.setDate(date);
		//LaptopDto laptopDto = new LaptopDto(5000,d3,"tem","alalpaplal",245,CategoryType.LAPTOP,
		//"tbrand","tmodel",8,512,ProcessorType.AMD,ProcessorModelType.RYZEN5,ColorType.MAVI);
		laptopService.save(laptop);
		return "aa";
	}
	
	
}
