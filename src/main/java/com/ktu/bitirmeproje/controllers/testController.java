package com.ktu.bitirmeproje.controllers;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.ktu.bitirmeproje.business.service.UserAccountService;
import com.ktu.bitirmeproje.utils.CategoryType;
import com.ktu.bitirmeproje.utils.ColorType;
import com.ktu.bitirmeproje.utils.HeadPhoneType;
import com.ktu.bitirmeproje.utils.PhoneType;
import com.ktu.bitirmeproje.utils.ProcessorModelType;
import com.ktu.bitirmeproje.utils.ProcessorType;
import com.ktu.bitirmeproje.utils.ReqBodyLogin;


@RestController
@RequestMapping("test")
public class testController {
	

	@Autowired
	private UserAccountService uaService;
	
	@Autowired
	private PhoneService phoneService;
	
	@Autowired
	private TelevisionService tvService;
	
	@Autowired
	private LaptopService laptopService;
	
	@Autowired
	private HeadPhoneService hpService;
	
	@Autowired TabletService tabletService;
	
	@GetMapping("a")
	public void deneme() {
//		Address a1 = new Address("istanbul", "bahcelievler", "lale", "10");
//
//		CustomerDto c1 = new CustomerDto("mustaf12","mustafa","aydin","ms25@hotmail.com","41587",a1); 
//		Address a2 = new Address("ankara", "gungoren", "merter", "10");
//
//		UserAccountDto s1 = new UserAccountDto("lexxr3","LEKAR","gulcan","ozturk","gulcan21@hotmail.com","sdq5450",a2); 
//		
//		uaService.save(s1);
//		uaService.save(c1);
//		
	}
	

	
	@GetMapping("/aa")
	public String aa()
	{
		return "asafdaf";

	}
	
	@GetMapping("/telefon")
	public void testtel() {
		Date d1 = new Date();
		PhoneDto pdto = new PhoneDto(3000,d1,"tem","afasdfasfafafafv",245,CategoryType.PHONE,"tbrand","tmodel",4,3,3,ColorType.BEYAZ,PhoneType.ANDROID);
		phoneService.save(pdto);
	}
	
	@GetMapping("/tv")
	public void testtv(){
		Date d2 = new Date();
		TelevisionDto tvDto = new TelevisionDto(3000,d2,"tem","afasdfasfafafafv",245,CategoryType.TELEVISION,"tbrand","tmodel",54,ColorType.SIYAH);
		tvService.save(tvDto);
	}
	
	@GetMapping("/laptop")
	public void laptop() {
		Date d3 = new Date();
		LaptopDto laptopDto = new LaptopDto(5000,d3,"tem","alalpaplal",245,CategoryType.LAPTOP,"tbrand","tmodel",8,512,ProcessorType.AMD,ProcessorModelType.RYZEN5,ColorType.MAVI);
		laptopService.save(laptopDto);
	}
	
	@GetMapping("/kulaklik")
	public void kulak() {
		Date d4 = new Date();
		HeadPhoneDto hpDto = new HeadPhoneDto(5000,d4,"tem","alalpaplal",245,CategoryType.HEADPHONE,HeadPhoneType.KABLOLU,ColorType.KIRMIZI);
		hpService.save(hpDto);
	}
	
	
	@GetMapping("/tablet")
	public void tablet() {
	    Date d5 = new Date();
	    TabletDto tabletDto = new TabletDto(PhoneType.IOS,2400,d5,"tem","tablettt",245,CategoryType.TABLET,"tbrand","tmodel",2,2,128,ColorType.BEYAZ);
	    tabletService.save(tabletDto);
	}

}
