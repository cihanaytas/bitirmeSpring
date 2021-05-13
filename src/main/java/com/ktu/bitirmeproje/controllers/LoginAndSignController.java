package com.ktu.bitirmeproje.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.ktu.bitirmeproje.business.dto.UserAccountDto;
import com.ktu.bitirmeproje.business.service.PasswordService;
import com.ktu.bitirmeproje.business.service.UserAccountService;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.utils.ReqBodyLogin;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("new")
public class LoginAndSignController {
	
	@Autowired
	private UserAccountService uaService;
	
	@Autowired
	private PasswordService passwordService;

	
	@PostMapping("/login")
	public boolean login(@RequestBody ReqBodyLogin reqBody)  {
		boolean sonuc = uaService.login(reqBody);
		return sonuc;
	}

	@PostMapping("/adduser")
	public ResponseEntity<?> addCustomer(@Valid @RequestBody UserAccountDto user) {
		return uaService.save(user);		  
	}
	
	@PostMapping("adduserdetail/{nickName}")
	public void adddetail(@PathVariable("nickName") String nickName) {
		uaService.adddetail(nickName);
	}
	
	
	
	@GetMapping("/userrole/{username}")
	public String getUserRole(@PathVariable("username") String username) {
		String role = uaService.getUserRole(username);
		return role;
	}
	
	@GetMapping("/parola/{mail}")
	public ResponseEntity<?> password(@PathVariable(name="mail") String mail){
		return passwordService.sendCode(mail);
	}
	
	@PostMapping("parola/{code}")
	public ResponseEntity<?> checkCode(@PathVariable(name="code") String code) {
		return passwordService.checkCode(code);
	}
	
	
	@PostMapping("parola")
	public ResponseEntity<?> setPassword(@Valid @RequestBody ReqBodyLogin reqBody) {
		return passwordService.setPassword(reqBody);
	}
	
	
	
	
}
