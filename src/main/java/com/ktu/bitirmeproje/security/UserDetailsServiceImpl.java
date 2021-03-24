package com.ktu.bitirmeproje.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.repository.UserAccountRepository;


 

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserAccountRepository uaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String password;
		String role;
		
		Optional<UserAccount> ua = uaRepository.findById(username);
		password = ua.get().getPassword();
		role = ua.get().getRole().toString();
				
		return User
				.withUsername(username)
				.password(passwordEncoder.encode(password))
				.roles(role)
				.build();
	}

	
}