package com.ktu.bitirmeproje.business.service;

import com.ktu.bitirmeproje.business.dto.prod.PhoneDto;
import com.ktu.bitirmeproje.data.entity.prod.Phone;

public interface PhoneService {
	
	public Phone save(PhoneDto phoneDto);

}
