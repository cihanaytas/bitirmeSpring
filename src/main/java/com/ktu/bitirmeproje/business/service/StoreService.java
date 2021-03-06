package com.ktu.bitirmeproje.business.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ktu.bitirmeproje.business.dto.StoreDto;
import com.ktu.bitirmeproje.business.dto.prod.NotificationProductDto;
import com.ktu.bitirmeproje.exception.ResourceNotFoundException;
import com.ktu.bitirmeproje.utils.ReqBodyLogin;

public interface StoreService {

	List<NotificationProductDto> getNotifications();

	public void notificationConfirmation(Long notificationID, Boolean onay);
	
 

}
