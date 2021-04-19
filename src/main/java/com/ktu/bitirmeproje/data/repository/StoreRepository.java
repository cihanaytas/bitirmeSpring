package com.ktu.bitirmeproje.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ktu.bitirmeproje.data.entity.StoreDetails;
import com.ktu.bitirmeproje.data.entity.UserAccount;

@Repository
public interface StoreRepository extends CrudRepository<StoreDetails, Long>{
	
	@Query("select s from StoreDetails s where s.store= :store")
	public StoreDetails getStoreDetail(@Param(value="store") UserAccount store);

}
