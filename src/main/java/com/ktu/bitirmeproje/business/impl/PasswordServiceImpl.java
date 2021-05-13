package com.ktu.bitirmeproje.business.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ktu.bitirmeproje.business.dto.UserAccountDto;
import com.ktu.bitirmeproje.business.dto.prod.ShoppingDto;
import com.ktu.bitirmeproje.business.service.PasswordService;
import com.ktu.bitirmeproje.data.entity.PasswordCode;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.repository.PasswordCodeRepository;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;
import com.ktu.bitirmeproje.exception.ErrorDetails;
import com.ktu.bitirmeproje.utils.ReqBodyLogin;

@Service
public class PasswordServiceImpl implements PasswordService{

	@Autowired
	private SendMail mailSender;
	
	@Autowired
	private UserAccountRepository userRep;
	
	@Autowired
	private PasswordCodeRepository pcRep;
	
	@Override
	public ResponseEntity<?> sendCode(String mail) {
		UserAccount user = userRep.findByE_mail(mail);
		if(user==null) {
			ErrorDetails err = new ErrorDetails(null, null, "Mail Adresi Bulunamıyor.");
			return ResponseEntity.badRequest().body(err);
		}
		Random rand = new Random();
		String code = String.format("%04d", rand.nextInt(10000));
		String text = "Parola yenileme talebinde bulundunuz. Parola yenileme için kodunuz " + code + ".";
		String subject = "Parola Değişikliği";
		mailSender.sendMail(mail, text, subject);
		addCodeTable(mail, code);
		return ResponseEntity.ok().body("Kod Gönderildi");
	}
	
	
	@Async
	private void addCodeTable(String mail, String code) {
		UserAccount user = userRep.findByE_mail(mail);
		PasswordCode pass = pcRep.findByUser(user);
		if(pass==null) {
			PasswordCode p = new PasswordCode();
			p.setUser(user);
			p.setCode(code);
			pcRep.save(p);
		}else {
			pass.setCode(code);
			pcRep.save(pass);
		}
		


	}


	@Override
	public ResponseEntity<?> checkCode(String code) {

		PasswordCode p = pcRep.findByCode(code);
		
		if(p==null)
		{	
			ErrorDetails err = new ErrorDetails(null, null, "Kod hatalı.");
			return ResponseEntity.badRequest().body(err);
		}
		
		else
			return ResponseEntity.ok().body("Başarılı");

			
	}


	
	
	@Override
	public ResponseEntity<?> setPassword(ReqBodyLogin reqBody){
		try {
			UserAccount user = userRep.findByE_mail(reqBody.getUsername());
			if(user==null) {
				ErrorDetails err = new ErrorDetails(null, null, "Mail Adresi Bulunamıyor.");
				
				return ResponseEntity.badRequest().body(err);
			}
			user.setPassword(reqBody.getPassword());
					
			return ResponseEntity.ok(userRep.save(user));
					
		}
		
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
