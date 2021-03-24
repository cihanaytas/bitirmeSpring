package com.ktu.bitirmeproje.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ktu.bitirmeproje.business.dto.CustomerDto;
import com.ktu.bitirmeproje.business.dto.StoreDto;
import com.ktu.bitirmeproje.business.service.StoreService;
 
import com.ktu.bitirmeproje.data.repository.StoreRepository;
import com.ktu.bitirmeproje.exception.ResourceNotFoundException;
import com.ktu.bitirmeproje.utils.ReqBodyLogin;
import com.ktu.bitirmeproje.utils.UserRole;


@Service
@Transactional
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	private StoreRepository storeRepository;
 

}
