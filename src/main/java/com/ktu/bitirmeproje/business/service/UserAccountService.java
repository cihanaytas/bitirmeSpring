package com.ktu.bitirmeproje.business.service;

import org.springframework.http.ResponseEntity;

import com.ktu.bitirmeproje.business.dto.UserAccountDto;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.utils.ReqBodyLogin;

public interface UserAccountService {

	public boolean login(ReqBodyLogin reqBody);
	public ResponseEntity<?> save(UserAccountDto uaDto);
	public void adddetail(String nickname);
	public String getUserRole(String username);
}
