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
	
	@GetMapping("test/a")
	public String tsas() {
		return "2 bas";
	}
	
	
	@GetMapping("test/a/b")
	public String tsss() {
		return "3 ";
	}
	
	@GetMapping("test/a/b/c")
	public String tssds() {
		return "3d ";
	}
	
}
