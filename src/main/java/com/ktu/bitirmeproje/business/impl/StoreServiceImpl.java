package com.ktu.bitirmeproje.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ktu.bitirmeproje.business.dto.CustomerDto;
import com.ktu.bitirmeproje.business.dto.StoreDto;
import com.ktu.bitirmeproje.business.dto.prod.NotificationProductDto;
import com.ktu.bitirmeproje.business.service.StoreService;
import com.ktu.bitirmeproje.data.entity.StoreDetails;
import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.NotificationProduct;
import com.ktu.bitirmeproje.data.entity.prod.Product;
import com.ktu.bitirmeproje.data.repository.NotificationRepository;
import com.ktu.bitirmeproje.data.repository.ProductRepository;
import com.ktu.bitirmeproje.data.repository.StoreRepository;
import com.ktu.bitirmeproje.exception.ResourceNotFoundException;
import com.ktu.bitirmeproje.utils.ReqBodyLogin;
import com.ktu.bitirmeproje.utils.UserByAuth;
import com.ktu.bitirmeproje.utils.UserRole;


@Service
@Transactional
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private NotificationRepository nRep;
	
	@Autowired
	private UserByAuth uba;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<NotificationProductDto> getNotifications() {
		UserAccount store = uba.getUserByAuth();
		StoreDetails store2 = storeRepository.getStoreDetail(store);
		List<NotificationProduct> list = nRep.findByStoreOrderByDateDesc(store2);
		List<NotificationProductDto> listDto = new ArrayList<NotificationProductDto>();
		
		for(NotificationProduct np : list) {
			NotificationProductDto npDto = new NotificationProductDto();
			convertToDto(np, npDto);
			listDto.add(npDto);
			
		}
				
		return listDto;
	}
	
	
	@Override
	public void notificationConfirmation(Long notificationID, Boolean onay) {
		Optional<NotificationProduct> notification = nRep.findById(notificationID);
		NotificationProduct n = notification.get();
		if(onay==true) {
		Optional<Product> product = productRepository.findById(n.getProduct().getProductID());
		Product p = product.get();
		p.setUnits(p.getUnits()-n.getUnits());
		}
		
		n.setOnay(onay);
		nRep.save(n);
		
	}
	
	
	
	private void convertToDto(NotificationProduct np,NotificationProductDto npDto) {
		npDto.setID(np.getID());
		npDto.setBildirim(np.getBildirim());
		npDto.setCustomerDetailId(np.getCustomer().getId());
		npDto.setDate(np.getDate());
		npDto.setProductId(np.getProduct().getProductID());
		npDto.setStoreDetailId(np.getStore().getId());
		npDto.setOkundu(np.getOkundu());
		npDto.setOnay(np.getOnay());
		npDto.setImageURI(np.getProduct().getImages().get(0).getImageUri());
		npDto.setShopId(np.getShop().getID());
		
	}




 

}
