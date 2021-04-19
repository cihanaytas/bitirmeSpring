package com.ktu.bitirmeproje.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ktu.bitirmeproje.data.entity.UserAccount;
import com.ktu.bitirmeproje.data.entity.prod.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	
	@Query("select p from Product p order by date desc")
	public List<Product> getAllProduct();
	
	@Query("select p from Product p where p.store= :store  order by date desc")
	public List<Product> getAllProductByNickname(@Param("store") UserAccount store);
	

}
