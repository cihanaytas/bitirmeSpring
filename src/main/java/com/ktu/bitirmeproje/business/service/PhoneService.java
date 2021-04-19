package com.ktu.bitirmeproje.business.service;

import com.ktu.bitirmeproje.business.dto.prod.PhoneDto;
import com.ktu.bitirmeproje.data.entity.prod.Phone;
import com.ktu.bitirmeproje.data.entity.prod.Product;

public interface PhoneService {
	
	public Product save(PhoneDto phoneDto);

}
