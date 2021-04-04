package com.ktu.bitirmeproje.controllers.store.addproduct;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktu.bitirmeproje.business.dto.prod.HeadPhoneDto;
import com.ktu.bitirmeproje.business.dto.prod.LaptopDto;
import com.ktu.bitirmeproje.business.dto.prod.PhoneDto;
import com.ktu.bitirmeproje.business.dto.prod.TabletDto;
import com.ktu.bitirmeproje.business.dto.prod.TelevisionDto;
import com.ktu.bitirmeproje.business.service.HeadPhoneService;
import com.ktu.bitirmeproje.business.service.LaptopService;
import com.ktu.bitirmeproje.business.service.PhoneService;
import com.ktu.bitirmeproje.business.service.TabletService;
import com.ktu.bitirmeproje.business.service.TelevisionService;


@RestController
@RequestMapping("store")
public class StoreProductController {

	
	@Autowired
	private LaptopService laptopService;
	
	@Autowired
	private TabletService tabletService;
	
	@Autowired
	private PhoneService phoneService;
	
	@Autowired
	private TelevisionService tvService;
	
	@Autowired
	private HeadPhoneService hpService;


	
	
	@PostMapping("/addlaptop")
	public String addlaptop(@Valid @RequestBody LaptopDto laptop) {
		laptopService.save(laptop);
		return "aa";
	}
	
	
	@PostMapping("/addtablet")
	public String addtablet(@Valid @RequestBody TabletDto tablet) {

		tabletService.save(tablet);
		return "a";
		
	}
	
	
	@PostMapping("/addphone")
	public String addphone(@Valid @RequestBody PhoneDto phone) {
		phoneService.save(phone);
		return "a";
	}
	
	@PostMapping("/addtv")
	public String addtv(@Valid @RequestBody TelevisionDto tv) {
		tvService.save(tv);
		return "a";
	}
	
	@PostMapping("/addheadphone")
	public String addhp(@Valid @RequestBody HeadPhoneDto hp) {
		hpService.save(hp);
		return "a";
	}
	
	
	
	
}
