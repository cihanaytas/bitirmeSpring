package com.ktu.bitirmeproje.business.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service; 
import com.ktu.bitirmeproje.business.dto.UserAccountDto;
import com.ktu.bitirmeproje.business.service.UserAccountService;
import com.ktu.bitirmeproje.data.entity.StoreDetails;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.repository.CustomerRepository;
import com.ktu.bitirmeproje.data.repository.StoreRepository;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;
import com.ktu.bitirmeproje.exception.ErrorDetails;
import com.ktu.bitirmeproje.utils.ReqBodyLogin;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService{

	@Autowired
	private UserAccountRepository uaRepository;
	
	@Autowired
	private StoreRepository storeRep;
	
	@Autowired
	private CustomerRepository customerRep;
	
	
	public boolean login(ReqBodyLogin reqBody) {
		UserAccount ua = uaRepository.findById(reqBody.getUsername()).orElse(new UserAccount());
		
		if(ua.getNickName() != null ) {
			if(reqBody.getPassword().equals(ua.getPassword())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	

	
	@Override
	public ResponseEntity<?> save(UserAccountDto uaDto){
		try {
			
			if(uaRepository.existsById(uaDto.getNickName())) {
				ErrorDetails err = new ErrorDetails(null, null, "Bu kullanıcı adı zaten kullanılıyor. Lütfen başka bir kullanıcı adı giriniz.");
				
				return ResponseEntity.badRequest()
						.body(err);
			}
			
			if(uaRepository.existsByMail(uaDto.getE_mail())) {
				ErrorDetails err = new ErrorDetails(null, null, "Bu mail adresi sistemde kayıtlı. Lütfen başka bir mail adresi giriniz.");
				
				return ResponseEntity.badRequest()
						.body(err);
			}
			

			
			
			UserAccount ua = new UserAccount();
			convertToEntity(ua, uaDto);

			
			
			return ResponseEntity.ok(uaRepository.save(ua));
					
		}
		
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void adddetail(String nickname) {
		Optional<UserAccount> user = uaRepository.findById(nickname);
		if(user.get().getRole().toString().equals("STORE")) {
			StoreDetails store = new StoreDetails();
			store.setStore(user.get());
			storeRep.save(store);
		}
		else if(user.get().getRole().toString().equals("CUSTOMER")) {
			
		}
		
	}
	

	
	
	@Override
	public String getUserRole(String username) {
		
		return uaRepository.getUserRole(username);
	}
	
	
	
	
	
	public void convertToEntity(UserAccount ua, UserAccountDto uaDto) {		
		ua.setNickName(uaDto.getNickName());
		ua.setName(uaDto.getName());
		ua.setSurname(uaDto.getSurname());
		ua.setE_mail(uaDto.getE_mail());
		ua.setPassword(uaDto.getPassword());
		ua.setAddress(uaDto.getAddress());
		ua.setRole(uaDto.getRole());
	}
	
	
	public void convertToDto(UserAccount ua, UserAccountDto uaDto) {
		uaDto.setNickName(ua.getNickName());
		uaDto.setName(ua.getName());
		uaDto.setSurname(ua.getSurname());
		uaDto.setE_mail(ua.getE_mail());
		uaDto.setAddress(ua.getAddress());
		uaDto.setRole(ua.getRole());
	}








}