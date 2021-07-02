package com.ktu.bitirmeproje.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ktu.bitirmeproje.data.entity.CustomerDetails;
import com.ktu.bitirmeproje.data.entity.StoreDetails;
import com.ktu.bitirmeproje.data.entity.prod.NotificationProduct;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationProduct, Long>{
	
	@Query("select n from NotificationProduct n where n.store= :store and n.onay is null order by date desc")
	List<NotificationProduct> findByStoreOrderByDateDesc(StoreDetails store);
	
	@Query("select n from NotificationProduct n where n.store= :store and n.onay is not null order by date desc")
	List<NotificationProduct> oldfindByStoreOrderByDateDesc(StoreDetails store);
	
	@Query("select n from NotificationProduct n where n.customer= :customer and n.onay is not null order by date desc")
	List<NotificationProduct> findByCustomer(CustomerDetails customer);


}
