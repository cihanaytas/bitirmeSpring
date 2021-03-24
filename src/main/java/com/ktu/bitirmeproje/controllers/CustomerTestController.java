package com.ktu.bitirmeproje.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerTestController {

	
	@GetMapping("test")
	public String ts() {
		return "basarili";
	}
	
	@GetMapping("test2")
	public String tss() {
		return "2 bas";
	}
	
}
