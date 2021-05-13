package com.ktu.bitirmeproje.business.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.ktu.bitirmeproje.utils.ReqBodyLogin;

public interface PasswordService {

	public ResponseEntity<?> sendCode(String mail);

	public ResponseEntity<?> checkCode(String code);

	public ResponseEntity<?> setPassword(ReqBodyLogin reqBody);
}
