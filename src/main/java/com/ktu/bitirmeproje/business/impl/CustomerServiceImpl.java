package com.ktu.bitirmeproje.business.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ktu.bitirmeproje.business.dto.CustomerDto;
import com.ktu.bitirmeproje.business.service.CustomerService;
import com.ktu.bitirmeproje.data.repository.CustomerRepository;
import com.ktu.bitirmeproje.exception.ResourceNotFoundException;
import com.ktu.bitirmeproje.utils.ReqBodyLogin;
import com.ktu.bitirmeproje.utils.UserRole;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	

 

	
	
	
	



}
