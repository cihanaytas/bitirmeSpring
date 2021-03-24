package com.ktu.bitirmeproje.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/customer/**").hasRole("CUSTOMER")
			.antMatchers("/store/**").hasRole("STORE")
		    //.antMatchers("/test/**","/addcustomer","/login").permitAll()
		    .antMatchers("/new/**").permitAll()
			.anyRequest()
			.authenticated()
		.and()
		    .formLogin().disable()
			.csrf().disable()
			.httpBasic()
		.and()
			.logout()
			.logoutSuccessUrl("/logout")
			.invalidateHttpSession(true)
			.permitAll();
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.userDetailsService(userDetailsService())
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
