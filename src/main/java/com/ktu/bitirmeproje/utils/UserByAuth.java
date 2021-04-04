package com.ktu.bitirmeproje.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;

@Component
public class UserByAuth {

	@Autowired
	private UserAccountRepository urep;
	
	
	public UserAccount getUserByAuth() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<UserAccount> user = urep.findById(authentication.getName());
		return user.get();
	}
	
}
