package com.ktu.bitirmeproje.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktu.bitirmeproje.utils.ReqBodyLogin;

@RestController
@RequestMapping("store")
public class StoreTestController {
	
	
	@GetMapping("test")
	public String sts() {
		return "basarili aaa";
	}
	
	@GetMapping("xx")
	public ReqBodyLogin as() {
		ReqBodyLogin nr = new ReqBodyLogin("cihan","parola");
		return nr;
	}

}
