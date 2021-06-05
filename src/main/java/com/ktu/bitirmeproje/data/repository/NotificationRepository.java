package com.ktu.bitirmeproje.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.StoreDetails;
import com.ktu.bitirmeproje.data.entity.prod.NotificationProduct;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationProduct, Long>{
	
	List<NotificationProduct> findByStoreOrderByDateDesc(StoreDetails store);

}
